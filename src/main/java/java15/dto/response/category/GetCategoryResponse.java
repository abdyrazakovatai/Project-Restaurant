package java15.dto.response.category;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record GetCategoryResponse(
        Long id,
        String name,
        HttpStatus httpStatus,
        String message
) {
}
