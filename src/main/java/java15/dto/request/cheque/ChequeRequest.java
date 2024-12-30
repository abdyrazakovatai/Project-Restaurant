package java15.dto.request.cheque;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChequeRequest {
    @NotNull(message = "ID не может быть пустым")
    private Long menuItemId;

    @NotNull(message = "ID не может быть пустым")
    private Long chequeId;
}
