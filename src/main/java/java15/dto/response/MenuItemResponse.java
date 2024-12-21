package java15.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record MenuItemResponse(
        Long restaurantId,
        Long menuId,
        HttpStatus httpStatus,
        String message
) {
}
