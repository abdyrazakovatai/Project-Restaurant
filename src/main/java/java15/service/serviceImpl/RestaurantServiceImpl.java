package java15.service.serviceImpl;

import java15.dto.request.RestaurantRequest;
import java15.dto.response.RestaurantResponse;
import java15.exception.BadRequestException;
import java15.model.Restaurant;
import java15.repo.RestaurantRepository;
import java15.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

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
}
