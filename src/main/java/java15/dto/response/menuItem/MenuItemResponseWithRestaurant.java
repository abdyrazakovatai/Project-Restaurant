package java15.dto.response.menuItem;

import java.math.BigDecimal;

public record MenuItemResponseWithRestaurant(
        String name,
        BigDecimal price
) {
}
