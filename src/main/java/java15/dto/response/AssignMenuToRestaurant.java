package java15.dto.response;

import org.springframework.http.HttpStatus;

public record AssignMenuToRestaurant(
        Long categoryId,
        Long menuItemId,
        HttpStatus httpStatus,
        String message
) {
}
