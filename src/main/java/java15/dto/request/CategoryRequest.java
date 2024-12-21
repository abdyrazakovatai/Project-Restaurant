package java15.dto.request;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CategoryRequest {
    private String name;
}
