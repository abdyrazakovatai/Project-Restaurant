package java15.api;

import java15.dto.request.auth.LoginRequest;
import java15.dto.response.auth.LoginResponse;
import java15.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthAPI {
    private final EmployeeService employeeService;

    @PostMapping("/login")
    public LoginResponse login (@RequestBody LoginRequest loginRequest){
        return employeeService.login(loginRequest);
    }
}
