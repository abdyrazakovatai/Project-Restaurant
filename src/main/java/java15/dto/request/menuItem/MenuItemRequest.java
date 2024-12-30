package java15.dto.request.menuItem;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
public class MenuItemRequest {

    private Long restaurantId;
//    private Long employeeId;
    private Long subcategoryId;
    //    private Subcategory subcategory;
    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 4,max = 30)
    private String name;

    @NotBlank(message = "Поле не может быть пустым")
    private String image;

    @NotNull(message = "Цена не может быть пустой")
    @DecimalMin(value = "0.0", inclusive = false, message = "Цена должна быть больше 0")
    private BigDecimal price;

    @NotBlank(message = "Описание не может быть пустым")
    private String description;

    @NotNull(message = "Поле vegetarian не может быть пустым")
//    @AssertTrue(message = "Поле не может быть пустым")
    private Boolean isVegetarian;
}
