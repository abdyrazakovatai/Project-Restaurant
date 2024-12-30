package java15.service.serviceImpl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java15.config.jwt.JwtService;
import java15.dto.request.employee.BidEmployeeRequest;
import java15.dto.request.auth.LoginRequest;
import java15.dto.request.employee.SaveEmployeeRequest;
import java15.dto.response.auth.LoginResponse;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.employee.*;
import java15.enums.Bid;
import java15.enums.Role;
import java15.exception.BadRequestException;
import java15.exception.NotFoundException;
import java15.model.Employee;
import java15.model.MenuItem;
import java15.model.Restaurant;
import java15.repo.EmployeeRepository;
import java15.repo.RestaurantRepository;
import java15.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepo;
    private final RestaurantRepository restaurantRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @PostConstruct
    public void defaultAdmin() {
        boolean exists = employeeRepo.existsByEmail("admin@gmail.com");
        if (!exists) {
            employeeRepo.save(
                    Employee.builder()
                            .firstName("Admin")
                            .email("admin@gmail.com")
                            .password(passwordEncoder.encode("admin123"))
                            .role(Role.ADMIN)
                            .build());
        }
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Employee employee = employeeRepo.getEmployeeByEmail(loginRequest.getEmail());
        if (employee.getPassword().equals(passwordEncoder.encode(loginRequest.getPassword()))) {
            throw new BadRequestException("Wrong password");
        }
        return LoginResponse.builder()
                .employeeId(employee.getId())
                .firstName(employee.getFirstName())
                .email(employee.getEmail())
                .jwtToken(jwtService.createToken(employee))
                .role(employee.getRole())
                .build();
    }

    @Override
    public SaveEmployeeResponse saveEmployee(SaveEmployeeRequest saveEmployeeRequest) {
        Restaurant restaurant = restaurantRepo.getRestaurantById(saveEmployeeRequest.getRestaurantId());
        if (employeeRepo.existsByEmail(saveEmployeeRequest.getEmail())) {
            throw new NotFoundException("Email already exists");
        }
        Employee saveEmployee = employeeRepo.save(
                Employee.builder()
                        .firstName(saveEmployeeRequest.getFirstName())
                        .lastName(saveEmployeeRequest.getLastName())
                        .restaurant(restaurant)
                        .email(saveEmployeeRequest.getEmail())
                        .password(passwordEncoder.encode(saveEmployeeRequest.getPassword()))
                        .dateOfBirth(saveEmployeeRequest.getDateOfBirth())
                        .phoneNumber(saveEmployeeRequest.getPhoneNumber())
                        .role(saveEmployeeRequest.getRole())
                        .experience(saveEmployeeRequest.getExperience())
                        .build());

        restaurant.setNumberOfEmployees(restaurant.getNumberOfEmployees() + 1);
        restaurantRepo.save(restaurant);

        return SaveEmployeeResponse
                .builder()
                .restaurantId(restaurant.getId())
                .employeeId(saveEmployee.getId())
                .token(jwtService.createToken(saveEmployee))
                .email(saveEmployee.getEmail())
                .role(saveEmployee.getRole())
                .status(HttpStatus.OK)
                .message("Employee registered successfully")
                .build();
    }

    @Override
    public BidEmployeeResponse bidEmployee(BidEmployeeRequest bidEmployeeRequest) {
        if (employeeRepo.existsByEmail(bidEmployeeRequest.getEmail())) {
            throw new BadRequestException("Email with " + bidEmployeeRequest.getEmail() + " already exists");
        }
        Employee saveEmployee = employeeRepo.save(
                Employee.builder()
                        .firstName(bidEmployeeRequest.getFirstName())
                        .lastName(bidEmployeeRequest.getLastName())
                        .email(bidEmployeeRequest.getEmail())
                        .password(passwordEncoder.encode(bidEmployeeRequest.getPassword()))
                        .role(Role.EMPLOYEE)
                        .dateOfBirth(bidEmployeeRequest.getDateOfBirth())
                        .experience(bidEmployeeRequest.getExperience())
                        .phoneNumber(bidEmployeeRequest.getPhoneNumber())
                        .build());

        return BidEmployeeResponse
                .builder()
                .employeeId(saveEmployee.getId())
                .httpStatus(HttpStatus.OK)
                .message("Employee registered successfully")
                .build();

    }

    @Transactional
    @Override
    public BidAcceptOrRejectResponse bidAcceptOrReject(Long employeeId, Long restaurantId, Bid bid, Role newRole) {

        Employee employee = employeeRepo.getAllByRoleById(employeeId);
        if (employee == null) {
            throw new NotFoundException("Employee not found");
        }
        Restaurant restaurant = restaurantRepo.getRestaurantById(restaurantId);

        if (Bid.ACCEPT.equals(bid)) {

            employee.setRestaurant(restaurant);
            restaurant.getEmployees().add(employee);
            employee.setRole(newRole);
            employeeRepo.save(employee);
            return BidAcceptOrRejectResponse.builder()
                    .employeeId(employee.getId())
                    .httpStatus(HttpStatus.OK)
                    .bidMessage("You are hired")
                    .message("Employee accepted")
                    .build();
        } else {
            employeeRepo.delete(employee);
            return BidAcceptOrRejectResponse.builder()
                    .employeeId(employee.getId())
                    .httpStatus(HttpStatus.OK)
                    .bidMessage("You are not accepted")
                    .message("Employee deleted")
                    .build();
        }
    }

    @Override
    public List<GetAllBidEmployeeResponse> findAll() {
        List<Employee> allEmployees = employeeRepo.getAllByRole();

        List<GetAllBidEmployeeResponse> responses = new ArrayList<>();

        allEmployees.stream().map(employee -> new GetAllBidEmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getRole()

        )).forEach(responses::add);

        return responses;
    }

    @Override
    public List<GetAllResponse> getAll() {
        List<Employee> allEmployees = employeeRepo.getAll();

        List<GetAllResponse> responses = new ArrayList<>();

        allEmployees.stream().map(employee -> new GetAllResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getRole(),
                employee.getExperience()
        )).forEach(responses::add);

        return responses;

    }

    @Override
    public SimpleResponse delete(Long id) {
        employeeRepo.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Employee deleted")
                .build();
    }

    @Override
    public SimpleResponse update(Long id, SaveEmployeeRequest updateEmployeeRequest) {
        Employee employee = employeeRepo.getEmployeeById(id);

        Restaurant newRestaurant = restaurantRepo.getRestaurantById(updateEmployeeRequest.getRestaurantId());

        employee.setRestaurant(newRestaurant);

        employee.setFirstName(updateEmployeeRequest.getFirstName());
        employee.setLastName(updateEmployeeRequest.getLastName());
        employee.setEmail(updateEmployeeRequest.getEmail());
        employee.setDateOfBirth(updateEmployeeRequest.getDateOfBirth());
        employee.setRole(updateEmployeeRequest.getRole());
        employee.setExperience(updateEmployeeRequest.getExperience());
        employeeRepo.save(employee);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Employee updated")
                .build();
    }

    @Override
    public PaginationResponse<GetAllResponse> getAllPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Employee> employeesPage = employeeRepo.findAll(pageable);

        List<GetAllResponse> responseList = new ArrayList<>();

        employeesPage.getContent().forEach(
                employee -> responseList.add(new GetAllResponse(employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getRole(),
                        employee.getExperience()))
        );

        PaginationResponse<GetAllResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setPageNumber(employeesPage.getNumber() + 1);
        paginationResponse.setPageSize(employeesPage.getSize());
        paginationResponse.setNumberOfPages(employeesPage.getTotalPages());
        paginationResponse.setNumberOrElements(employeesPage.getTotalElements());
        paginationResponse.setObjects(responseList);
        return paginationResponse;
    }

    @Override
    public GetAllResponse findById(Long id) {
        Employee employee = employeeRepo.getEmployeeById(id);
        return GetAllResponse.builder()
                .employeeId(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .role(employee.getRole())
                .experience(employee.getExperience())
                .build();
    }

}
