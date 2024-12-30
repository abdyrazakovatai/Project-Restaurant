package java15.api;

import jakarta.validation.Valid;
import java15.dto.request.restaurant.RestaurantRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.restaurant.GetRestaurantResponse;
import java15.dto.response.restaurant.RestaurantResponse;
import java15.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
@Validated
public class RestaurantAPI {

    private final RestaurantService restaurantService;

    @Secured("ADMIN")
    @PostMapping("/addRestaurant")
    public RestaurantResponse createRestaurant  (@Valid @RequestBody RestaurantRequest restaurantRequest) {
        return restaurantService.save(restaurantRequest);
    }

    @Secured("ADMIN")
    @GetMapping ("/getRestaurant{id}")
    public List<GetRestaurantResponse> getRestaurant(@PathVariable Long id){
        return restaurantService.getRestaurant(id);
    }

    @PutMapping("/updateRestaurant{id}")
    public SimpleResponse updateRestaurant(@Valid @PathVariable Long id , RestaurantRequest restaurantRequest) {
        return restaurantService.update(id,restaurantRequest);
    }

    @Secured("ADMIN")
    @DeleteMapping ("/delete{id}")
    public SimpleResponse deleteRestaurant (@Valid @PathVariable Long id) {
        return restaurantService.delete(id);
    }
}