package java15.dto.response.subcategory;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record SubcategoryResponse(
        Long subcategoryId,
        Long categoryId,
        String name,
        HttpStatus httpStatus,
        String message
) {
}
