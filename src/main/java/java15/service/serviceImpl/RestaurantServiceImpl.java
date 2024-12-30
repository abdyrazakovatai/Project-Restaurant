package java15.service.serviceImpl;

import jakarta.transaction.Transactional;
import java15.dto.request.restaurant.RestaurantRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.employee.GetAllResponse;
import java15.dto.response.menuItem.MenuItemResponseWithRestaurant;
import java15.dto.response.restaurant.GetRestaurantResponse;
import java15.dto.response.restaurant.RestaurantResponse;
import java15.exception.BadRequestException;
import java15.model.Employee;
import java15.model.MenuItem;
import java15.model.Restaurant;
import java15.repo.EmployeeRepository;
import java15.repo.RestaurantRepository;
import java15.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public RestaurantResponse save(RestaurantRequest restaurantRequest) {
        if (restaurantRepository.existsByName(restaurantRequest.getName())) {
            throw new BadRequestException("Restaurant name already exists");
        }

        Restaurant saveRestaurant = restaurantRepository.save(
                Restaurant.builder()
                        .name(restaurantRequest.getName())
                        .location(restaurantRequest.getLocation())
                        .restType(restaurantRequest.getRestType())
                        .service(restaurantRequest.getService())
                        .build());
        return RestaurantResponse.builder()
                .name(saveRestaurant.getName())
                .httpStatus(HttpStatus.OK)
                .message("Restaurant saved successfully")
                .restType(saveRestaurant.getRestType())
                .build();
    }

    @Transactional
    @Override
    public List<GetRestaurantResponse> getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.getRestaurantById(id);

        List<Employee> employees = restaurant.getEmployees();

        List<GetAllResponse> getAllList = employees.stream().map(employee -> new GetAllResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getRole(),
                employee.getExperience()
        )).toList();

        List<MenuItem> menuItems = restaurant.getMenuItems();
        List<MenuItemResponseWithRestaurant> itemList = menuItems.stream().map(item -> new MenuItemResponseWithRestaurant(
                item.getName(),
                item.getPrice()
        )).toList();

        String location = restaurant.getLocation();
        String name = restaurant.getName();

        GetRestaurantResponse getRestaurantResponse = new GetRestaurantResponse(
                restaurant.getId(),
                name,
                location,
                itemList,
                getAllList
        );
        return List.of(getRestaurantResponse);
    }

    @Transactional
    @Override
    public SimpleResponse delete(Long id) {
        Restaurant restaurant = restaurantRepository.getRestaurantById(id);
        employeeRepository.deleteAll(restaurant.getEmployees());

        restaurantRepository.delete(restaurant);

        return new SimpleResponse(HttpStatus.OK, "Restaurant deleted successfully");
    }

    @Override
    public SimpleResponse update(Long id, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepository.getRestaurantById(id);

        if (restaurant == null) {
            return new SimpleResponse(HttpStatus.NOT_FOUND, "Restaurant not found");
        }

        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setRestType(restaurantRequest.getRestType());
        restaurant.setService(restaurantRequest.getService());

        restaurantRepository.save(restaurant);

        return new SimpleResponse(HttpStatus.OK, "Restaurant updated successfully");
    }
}