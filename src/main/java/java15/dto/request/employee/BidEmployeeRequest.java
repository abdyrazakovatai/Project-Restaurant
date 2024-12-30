package java15.dto.request.employee;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BidEmployeeRequest {
    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 4,max = 30,message = "Имя должно быть от 4 до 30 символов")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустым")
    @Size(min = 4,max = 30,message = "Фамилия должно быть от 4 до 30 символов")
    private String lastName;

    @NotNull(message = "Дата рождения не может быть пустым")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Email не может быть пустым")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@(gmail\\.com|yahoo\\.com|[A-Za-z0-9.-]+\\.org)$",
            message = "Email должен быть @gmail.com, @yahoo.com или заканчиваться на .org")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 8, max = 20, message = "Пароль должен быть от 8 до 20 символов")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Пароль должен быть не менее 8 символов, содержать хотя бы одну букву и одну цифру")
    private String password;

    @Pattern(regexp = "^\\+996\\d{9}$",
            message = "Номер должен начинаться с +996 и содержать 9 цифр")
    @NotBlank(message = "Номер не должен быть пустым")
    private String phoneNumber;

    private int experience;
}
