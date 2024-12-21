package java15.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record AssignResponse(
        Long employeeId,
        Long restaurantId,
        HttpStatus httpStatus,
        String message
) {
}
