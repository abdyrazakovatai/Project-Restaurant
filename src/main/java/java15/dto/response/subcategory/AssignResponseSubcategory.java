package java15.dto.response.subcategory;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record AssignResponseSubcategory(
        Long categoryId,
        Long subcategoryId,
        HttpStatus httpStatus,
        String message
        ) {
}
