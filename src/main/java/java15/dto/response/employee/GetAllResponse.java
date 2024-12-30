package java15.dto.response.employee;

import java15.enums.Role;
import lombok.Builder;

@Builder
public record GetAllResponse(
        Long employeeId,
        String firstName,
        String lastName,
        Role role,
        int experience
) {
}
