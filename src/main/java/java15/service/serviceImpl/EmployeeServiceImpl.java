package java15.service.serviceImpl;

import jakarta.annotation.PostConstruct;
import java15.config.jwt.JwtService;
import java15.dto.request.BidEmployeeRequest;
import java15.dto.request.LoginRequest;
import java15.dto.request.SaveEmployeeRequest;
import java15.dto.response.*;
import java15.enums.Bid;
import java15.enums.Role;
import java15.exception.BadRequestException;
import java15.exception.NotFoundException;
import java15.model.Employee;
import java15.model.Restaurant;
import java15.repo.EmployeeRepository;
import java15.repo.RestaurantRepository;
import java15.service.EmployeeService;
import lombok.RequiredArgsConstructor;
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

//    @Override
//    public RegisterResponse signUp(RegisterRequest registerRequest) {
//        if (employeeRepo.existsByEmail(registerRequest.getEmail())) {
//            throw new BadRequestException("Email already exists");
//        }
//        Employee saveEmployee = employeeRepo.save(
//                Employee.builder()
//                        .firstName(registerRequest.getFirstName())
//                        .lastName(registerRequest.getLastName())
//                        .email(registerRequest.getEmail())
//                        .password(passwordEncoder.encode(registerRequest.getPassword()))
//                        .dateOfBirth(registerRequest.getDateOfBirth())
//                        .phoneNumber(registerRequest.getPhoneNumber())
//                        .role(Role.EMPLOYEE)
//                        .experience(registerRequest.getExperience())
//                        .build());
//
//        return RegisterResponse
//                .builder()
//                .employeeId(saveEmployee.getId())
//                .token(jwtService.createToken(saveEmployee))
//                .email(saveEmployee.getEmail())
//                .role(saveEmployee.getRole())
//                .status(HttpStatus.OK)
//                .message("Employee registered successfully")
//                .build();
//    }

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

//    @Override
//    public AssignResponse assignChef(Long employeeId, Long restaurantId) {
//        Employee employee = employeeRepo.getEmployeeById(employeeId);
//        Restaurant restaurant = restaurantRepo.getRestaurantById(restaurantId);
//
//        restaurant.getEmployees().add(employee);
//        employee.setRole(Role.CHEF);
//        employee.setRestaurant(restaurant);
//        employeeRepo.save(employee);
//
//        return AssignResponse.builder()
//                .employeeId(employee.getId())
//                .restaurantId(restaurant.getId())
//                .httpStatus(HttpStatus.OK)
//                .message("Restaurant assigned successfully")
//                .build();
//    }
//
//    @Override
//    public AssignResponse assignWaiter(Long employeeId, Long restaurantId) {
//        Employee employee = employeeRepo.getEmployeeById(employeeId);
//        Restaurant restaurant = restaurantRepo.getRestaurantById(restaurantId);
//
//        restaurant.getEmployees().add(employee);
//        employee.setRole(Role.WAITER);
//        employee.setRestaurant(restaurant);
//        employeeRepo.save(employee);
//
//        return AssignResponse.builder()
//                .employeeId(employee.getId())
//                .restaurantId(restaurant.getId())
//                .httpStatus(HttpStatus.OK)
//                .message("Restaurant assigned successfully")
//                .build();
//    }

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

    @Override
    public BidAcceptOrRejectResponse bidAcceptOrReject(Long employeeId, Long restaurantId, Bid bid) {

        Employee employee = employeeRepo.getAllByRoleById(employeeId);
        if (employee == null) {
            throw new NotFoundException("Employee not found");
        }
        Restaurant restaurant = restaurantRepo.getRestaurantById(restaurantId);

        Bid accept = Bid.ACCEPT;

        if (bid.equals(accept)) {
            employee.setRestaurant(restaurant);
            restaurant.getEmployees().add(employee);
            employee.setRole(employee.getRole());
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
}
