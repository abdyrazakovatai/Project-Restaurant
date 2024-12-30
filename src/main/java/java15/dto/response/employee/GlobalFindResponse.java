package java15.dto.response.employee;

import java15.dto.response.menuItem.GetMenuItemResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record GlobalFindResponse (
        List<GetMenuItemResponse> menuItems
) {
}
