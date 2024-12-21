package java15.dto.request;

import java15.model.Subcategory;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
public class MenuItemRequest {
    private Long restaurantId;
    private Long employeeId;
    private Long subcategoryId;
//    private Subcategory subcategory;
    private String name;
    private String image;
    private BigDecimal price;
    private String description;
    private boolean isVegetarian;
}
