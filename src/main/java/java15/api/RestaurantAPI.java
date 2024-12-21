package java15.api;

import java15.dto.request.RestaurantRequest;
import java15.dto.response.RestaurantResponse;
import java15.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantAPI {

    private final RestaurantService restaurantService;

    @Secured("ADMIN")
    @PostMapping("/addRestaurant")
    public RestaurantResponse createRestaurant  (@RequestBody RestaurantRequest restaurantRequest) {
        return restaurantService.save(restaurantRequest);
    }
}