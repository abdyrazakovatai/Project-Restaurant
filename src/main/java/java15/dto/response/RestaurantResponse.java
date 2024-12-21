package java15.dto.response;

import java15.enums.RestType;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record RestaurantResponse(
        String name,
        RestType restType,
        int numberOfEmployees,
        HttpStatus httpStatus,
        String message
) {
}
