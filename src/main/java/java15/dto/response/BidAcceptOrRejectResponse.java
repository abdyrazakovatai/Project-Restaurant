package java15.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record BidAcceptOrRejectResponse(
        Long employeeId,
        HttpStatus httpStatus,
        String bidMessage,
        String message
) {
}
