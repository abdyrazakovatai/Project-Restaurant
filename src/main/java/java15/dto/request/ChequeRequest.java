package java15.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChequeRequest {
    private Long menuItemId;
    private Long chequeId;
}
