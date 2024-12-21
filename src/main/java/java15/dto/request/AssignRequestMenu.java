package java15.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AssignRequestMenu {
    private Long restaurantId;
    private Long menuItemId;
}
