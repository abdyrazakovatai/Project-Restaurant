package java15.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StopListRequest {
    private Long menuItemId;
    private String stopMessage;
}
