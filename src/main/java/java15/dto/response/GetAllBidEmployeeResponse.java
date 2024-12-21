package java15.dto.response;

import java15.enums.Role;
import lombok.Builder;

@Builder
public record GetAllBidEmployeeResponse(
        Long employeeId,
        String firstName,
        String lastName,
        String email,
        Role role
) {
}
