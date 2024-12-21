package java15.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssignRequest {
    private Long employeeId;
    private Long restaurantId;
}
