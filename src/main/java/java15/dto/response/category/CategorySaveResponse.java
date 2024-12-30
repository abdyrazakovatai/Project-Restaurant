package java15.dto.response.category;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record CategorySaveResponse(
        Long categoryId,
        String categoryName,
        HttpStatus httpStatus,
        String message
) {
}
