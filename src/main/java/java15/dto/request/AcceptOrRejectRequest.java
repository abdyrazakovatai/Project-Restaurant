package java15.dto.request;

import java15.enums.Bid;
import java15.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AcceptOrRejectRequest {
    private Long employeeId;
    private Long restaurantId;
    private Bid bid;
    private Role role;
}
