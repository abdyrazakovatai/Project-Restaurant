package java15.dto.response.menuItem;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record GetMenuItemResponse(
        Long id,
        String name,
        BigDecimal price,
        boolean isVegetarian
) {
}
