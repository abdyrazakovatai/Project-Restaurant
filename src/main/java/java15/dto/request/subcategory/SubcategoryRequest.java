package java15.dto.request.subcategory;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubcategoryRequest {
    private Long categoryId;
    private String name;
}
