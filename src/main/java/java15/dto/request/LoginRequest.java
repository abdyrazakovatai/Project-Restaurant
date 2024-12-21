package java15.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequest {
    private String firstName;
    private String email;
    private String password;

}
