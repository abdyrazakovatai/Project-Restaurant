package java15.dto.response;

import java15.enums.Role;
import lombok.Builder;

@Builder
public record LoginResponse(
        Long employeeId,
        String firstName,
        String email,
        Role role,
        JwtTokenResponse jwtToken
) {
}
