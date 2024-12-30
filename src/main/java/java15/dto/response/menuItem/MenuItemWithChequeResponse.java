package java15.dto.response.menuItem;

import java.math.BigDecimal;

public record MenuItemWithChequeResponse(
        String name,
        BigDecimal price,
        int quantity
) {
}
