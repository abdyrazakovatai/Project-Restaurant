package java15.api;

import java15.dto.request.LoginRequest;
import java15.dto.request.SaveEmployeeRequest;
import java15.dto.response.LoginResponse;
import java15.dto.response.SaveEmployeeResponse;
import java15.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthAPI {
    private final EmployeeService employeeService;

//    @PostMapping("/signUp")
//    public SaveEmployeeResponse signUp (@RequestBody SaveEmployeeRequest registerRequest ){
//        return employeeService.signUp(registerRequest);
//    }

    @PostMapping("/login")
    public LoginResponse login (@RequestBody LoginRequest loginRequest){
        return employeeService.login(loginRequest);
    }
}
