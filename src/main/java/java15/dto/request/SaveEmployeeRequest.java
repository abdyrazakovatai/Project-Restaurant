package java15.dto.request;

import java15.enums.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SaveEmployeeRequest {
    private Long restaurantId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;
    private int experience;
}
