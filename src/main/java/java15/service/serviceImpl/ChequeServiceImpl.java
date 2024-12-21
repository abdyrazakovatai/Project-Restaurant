package java15.service.serviceImpl;

import jakarta.transaction.Transactional;
import java15.dto.request.AddChequeRequest;
import java15.dto.request.AssignChequeToMenuItemResponse;
import java15.dto.response.AddChequeResponse;
import java15.dto.response.GetCheckResponse;
import java15.model.Cheque;
import java15.model.Employee;
import java15.model.MenuItem;
import java15.repo.ChequeRepository;
import java15.repo.EmployeeRepository;
import java15.repo.MenuItemRepository;
import java15.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepository chequeRepository;
    private final MenuItemRepository menuItemRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public AddChequeResponse addCheque(AddChequeRequest addChequeRequest) {
        Employee employee = employeeRepository.getEmployeeById(addChequeRequest.getEmployeeId());
        MenuItem menuItem = menuItemRepository.getMenuItemById(addChequeRequest.getMenuItemId());

        Hibernate.initialize(employee.getCheques());
        System.out.println("employee = " + employee);
        System.out.println("menuItem = " + menuItem);

        Cheque saveCheque = chequeRepository.saveAndFlush(
                Cheque.builder()
                        .employee(employee)
                        .menuItems(Arrays.asList(menuItem))
                        .createdAt(LocalDate.now())
                        .build());

        menuItem.getCheques().add(saveCheque);

//        chequeRepository.save(saveCheque);

        return AddChequeResponse.builder()
                .chequeId(saveCheque.getId())
                .httpStatus(HttpStatus.OK)
                .message("Cheque added success")
                .build();
    }

    @Override
    public GetCheckResponse getCheque(Long id) {
        Cheque cheque = chequeRepository.getChequeById(id);

        Employee employee = cheque.getEmployee();

        List<MenuItem> menuItems = cheque.getMenuItems();
        for (MenuItem menuItem : menuItems) {
            BigDecimal price = menuItem.getPrice();

        }
        return null;
    }
}
