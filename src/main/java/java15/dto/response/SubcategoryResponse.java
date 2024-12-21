package java15.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record SubcategoryResponse(
        Long subcategoryId,
        Long categoryId,
        HttpStatus httpStatus,
        String message
) {
}
