package java15.dto.response.stopList;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record StopListResponse(
        Long stopListId,
        Long menuItemId,
        HttpStatus httpStatus,
        String message
) {
}
