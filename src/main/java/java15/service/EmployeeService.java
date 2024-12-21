package java15.service;

import java15.dto.request.BidEmployeeRequest;
import java15.dto.request.LoginRequest;
import java15.dto.request.SaveEmployeeRequest;
import java15.dto.response.*;
import java15.enums.Bid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
//    RegisterResponse signUp(RegisterRequest registerRequest) ;

    LoginResponse login(LoginRequest loginRequest);

//    AssignResponse assignChef(Long employeeId, Long restaurantId);

//    AssignResponse assignWaiter(Long employeeId, Long restaurantId);

    SaveEmployeeResponse saveEmployee(SaveEmployeeRequest registerRequest);

    BidEmployeeResponse bidEmployee(BidEmployeeRequest bidEmployeeRequest);

    BidAcceptOrRejectResponse bidAcceptOrReject(Long employeeId, Long restaurantId, Bid bid);

    List<GetAllBidEmployeeResponse> findAll();
}
