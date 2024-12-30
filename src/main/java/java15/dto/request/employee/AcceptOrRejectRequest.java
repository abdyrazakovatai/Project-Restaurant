package java15.dto.request.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java15.enums.Bid;
import java15.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AcceptOrRejectRequest {

    @NotNull(message = "ID не может быть пустым")
    private Long employeeId;

    @NotNull(message = "ID не может быть пустым")
    private Long restaurantId;

    @NotNull
    private Bid bid;

    @NotNull
    private Role role;
}
