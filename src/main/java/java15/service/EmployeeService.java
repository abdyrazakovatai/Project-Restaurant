package java15.service;

import java15.dto.request.employee.BidEmployeeRequest;
import java15.dto.request.auth.LoginRequest;
import java15.dto.request.employee.SaveEmployeeRequest;
import java15.dto.response.auth.LoginResponse;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.employee.*;
import java15.enums.Bid;
import java15.enums.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    LoginResponse login(LoginRequest loginRequest);

    SaveEmployeeResponse saveEmployee(SaveEmployeeRequest registerRequest);

    BidEmployeeResponse bidEmployee(BidEmployeeRequest bidEmployeeRequest);

    BidAcceptOrRejectResponse bidAcceptOrReject(Long employeeId, Long restaurantId, Bid bid, Role newRole);

    List<GetAllBidEmployeeResponse> findAll();

    List<GetAllResponse> getAll();

    SimpleResponse delete(Long id);

    SimpleResponse update(Long id, SaveEmployeeRequest updateEmployeeRequest);

    PaginationResponse<GetAllResponse> getAllPagination(int pageNumber, int pageSize);

    GetAllResponse findById(Long id);
}
