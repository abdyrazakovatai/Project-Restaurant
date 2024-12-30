package java15.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequest {
    @NotBlank(message = "Email не может быть пустым")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@(gmail\\.com|yahoo\\.com|[A-Za-z0-9.-]+\\.org)$",
    message = "Email должен быть @gmail.com, @yahoo.com или заканчиваться на .org")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 8,max = 20,message = "Пароль должен быть от 8 до 20 символов")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Пароль должен быть не менее 8 символов, содержать хотя бы одну букву и одну цифру")
    private String password;

}
