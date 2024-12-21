package java15.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AddChequeRequest {
    private Long employeeId;
    private Long menuItemId;
    private LocalDate createdAt;
}
