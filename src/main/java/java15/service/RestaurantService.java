package java15.service;

import jakarta.validation.Valid;
import java15.dto.request.restaurant.RestaurantRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.restaurant.GetRestaurantResponse;
import java15.dto.response.restaurant.RestaurantResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
    RestaurantResponse save(RestaurantRequest restaurantRequest);

    List<GetRestaurantResponse> getRestaurant(Long id);

    SimpleResponse delete(Long id);

    SimpleResponse update(@Valid Long id, RestaurantRequest restaurantRequest);
}
