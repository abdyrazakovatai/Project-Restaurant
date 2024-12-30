package java15.dto.request.stopList;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StopListRequest {

    @NotNull(message = "ID  не может быть пустым")
    private Long menuItemId;

    @NotBlank(message = "Причина стоп-листа ?")
    private String stopMessage;
}
