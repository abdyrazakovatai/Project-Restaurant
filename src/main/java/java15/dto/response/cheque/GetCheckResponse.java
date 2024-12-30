package java15.dto.response.cheque;

import java15.dto.response.menuItem.MenuItemWithChequeResponse;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;

@Builder

public record GetCheckResponse(
        String restaurantName,
        String address,
        String waiterName,
        Long chequeId,
        List<MenuItemWithChequeResponse> menuItems,
        BigDecimal totalAmount,
        BigDecimal service,
        BigDecimal totalAmountIncludingService,
        HttpStatus httpStatus,
        String message
) {
}
