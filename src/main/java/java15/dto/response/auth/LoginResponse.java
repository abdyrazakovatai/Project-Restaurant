package java15.dto.response.auth;

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
