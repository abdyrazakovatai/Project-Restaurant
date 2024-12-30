package java15.dto.request.cheque;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class AddChequeRequest {
    @NotNull(message = "ID  не может быть пустым")
    private Long employeeId;

    private List<Long> menuItemsId;
    private LocalDate createdAt;
}
