package java15.service;

import java15.dto.request.RestaurantRequest;
import java15.dto.response.RestaurantResponse;
import org.springframework.stereotype.Service;

@Service
public interface RestaurantService {
    RestaurantResponse save(RestaurantRequest restaurantRequest);
}
