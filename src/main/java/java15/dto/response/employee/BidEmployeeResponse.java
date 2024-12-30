package java15.dto.response.employee;

import lombok.Builder;
import org.springframework.http.HttpStatus;
@Builder
public record BidEmployeeResponse(
        Long employeeId,
        HttpStatus httpStatus,
        String message
) {
}
