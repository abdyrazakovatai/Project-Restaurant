package java15.service.serviceImpl;

import jakarta.transaction.Transactional;
import java15.dto.request.cheque.AddChequeRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.cheque.AddChequeResponse;
import java15.dto.response.cheque.GetCheckResponse;
import java15.dto.response.menuItem.MenuItemWithChequeResponse;
import java15.enums.Role;
import java15.exception.BadRequestException;
import java15.model.Cheque;
import java15.model.Employee;
import java15.model.MenuItem;
import java15.repo.ChequeRepository;
import java15.repo.EmployeeRepository;
import java15.repo.MenuItemRepository;
import java15.repo.StopListRepository;
import java15.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepository chequeRepository;
    private final StopListRepository stopListRepository;
    private final MenuItemRepository menuItemRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public AddChequeResponse addCheque(AddChequeRequest addChequeRequest) {
        Employee employee = employeeRepository.getEmployeeById(addChequeRequest.getEmployeeId());
//        if (employee.getRole() != Role.ADMIN && employee.getRole() != Role.WAITER) {
//            throw new BadRequestException("You role no ADMIN or WAITER");
//        }

        List<MenuItem> menuItems = addChequeRequest.getMenuItemsId().stream().map(menuItemRepository::getMenuItemById).toList();

        Hibernate.initialize(employee.getCheques());

        for (MenuItem menuItem : menuItems) {
            boolean inStopList = stopListRepository.findStopListsById(menuItem.getId());
            if (inStopList) {
                throw new BadRequestException("MenuItem with id " + menuItem.getId() + " in stop list");
            }
        }

        Cheque saveCheque = chequeRepository.saveAndFlush(
                Cheque.builder()
                        .employee(employee)
                        .menuItems(menuItems)
                        .createdAt(LocalDate.now())
                        .build());

        menuItems.forEach(menuItem -> menuItem.getCheques().add(saveCheque));

        return AddChequeResponse.builder()
                .chequeId(saveCheque.getId())
                .httpStatus(HttpStatus.OK)
                .message("Cheque added success")
                .build();
    }

    @Transactional
    @Override
    public GetCheckResponse getCheque(Long id) {
        Cheque cheque = chequeRepository.getChequeById(id);

        Employee employee = cheque.getEmployee();

        List<MenuItem> menuItems = cheque.getMenuItems();

        Map<String, Map<BigDecimal, Long>> groupedItems = menuItems.stream()
                .collect(Collectors.groupingBy(
                        MenuItem::getName,
                        Collectors.groupingBy(MenuItem::getPrice, Collectors.counting())));

        List<MenuItemWithChequeResponse> item = groupedItems.entrySet().stream()
                .flatMap(entry -> entry.getValue().entrySet().stream()
                        .map(innerEntry -> new MenuItemWithChequeResponse(
                                entry.getKey(),
                                innerEntry.getKey(),
                                innerEntry.getValue().intValue()
                        ))
                ).toList();

        BigDecimal totalPrice = item.stream()
                .map(menuItem -> menuItem.price().multiply(BigDecimal.valueOf(menuItem.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal service = cheque.getEmployee().getRestaurant().getService();

        BigDecimal serviceCharge = totalPrice.multiply(service).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        BigDecimal totalServicePrice = totalPrice.add(serviceCharge);

        return GetCheckResponse.builder()
                .chequeId(id)
                .restaurantName(employee.getRestaurant().getName())
                .address(employee.getRestaurant().getLocation())
                .waiterName("Served you: " + employee.getFirstName())
                .menuItems(item)
                .totalAmountIncludingService(totalServicePrice)
                .totalAmount(totalPrice)
                .service(serviceCharge)
                .httpStatus(HttpStatus.OK)
                .message("Thanks for choose us")
                .build();
    }

    @Transactional
    @Override
    public SimpleResponse delete(Long id) {
        Cheque cheque = chequeRepository.getChequeById(id);
        if (cheque == null) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("Cheque not found")
                    .build();
        }

        cheque.getMenuItems().forEach(menuItem -> menuItem.getCheques().remove(cheque));

        menuItemRepository.deleteAll(cheque.getMenuItems());

        Employee employee = cheque.getEmployee();
        if (employee != null) {
            employee.getCheques().remove(cheque);
        }

        chequeRepository.delete(cheque);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Cheque deleted successfully")
                .build();
    }
}
