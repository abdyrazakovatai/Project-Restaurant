package java15.dto.response.employee;

import java15.dto.response.auth.JwtTokenResponse;
import java15.enums.Role;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record SaveEmployeeResponse(
        Long employeeId,
        Long restaurantId,
        JwtTokenResponse token,
        String email,
        Role role,
        HttpStatus status,
        String message
) {
}
