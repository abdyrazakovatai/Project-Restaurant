package java15.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

@Builder
@Getter
public record GetCheckResponse(
        Long employeeId,
        Long restaurantId,
        Long chequeId,
        BigDecimal price,
        HttpStatus httpStatus,
        String message
) {
}
