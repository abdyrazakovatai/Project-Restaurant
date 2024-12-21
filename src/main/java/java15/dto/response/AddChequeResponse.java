package java15.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record AddChequeResponse(
        Long chequeId,
        HttpStatus httpStatus,
        String message
) {
}
