package java15.api;

import jakarta.validation.Valid;
import java15.dto.request.employee.AcceptOrRejectRequest;
import java15.dto.request.employee.BidEmployeeRequest;
import java15.dto.request.employee.SaveEmployeeRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.employee.*;
import java15.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
@Validated
public class EmployeeAPI {

    private final EmployeeService employeeService;

    @Secured("ADMIN")
    @PostMapping("/saveEmployee")
    public SaveEmployeeResponse saveEmployee(@Valid @RequestBody SaveEmployeeRequest registerRequest) {
        return employeeService.saveEmployee(registerRequest);
    }

    @PutMapping("/updateEmployee{id}")
    public SimpleResponse updateEmployee (@PathVariable Long id, SaveEmployeeRequest updateEmployeeRequest){
        return employeeService.update(id,updateEmployeeRequest);
    }

    @PostMapping("/bidEmployee")
    public BidEmployeeResponse bidEmployee(@Valid @RequestBody BidEmployeeRequest bidEmployeeRequest){
        return employeeService.bidEmployee(bidEmployeeRequest);
    }

    @Secured("ADMIN")
    @PostMapping("/acceptOrReject")
    public BidAcceptOrRejectResponse acceptOrReject (@Valid @RequestBody AcceptOrRejectRequest acceptOrRejectRequest){
        return employeeService.bidAcceptOrReject(acceptOrRejectRequest.getEmployeeId(),acceptOrRejectRequest.getRestaurantId(),acceptOrRejectRequest.getBid(),acceptOrRejectRequest.getRole());
    }

    @Secured("ADMIN")
    @GetMapping("/getAllEmployee")
    public List<GetAllBidEmployeeResponse> getAll (){
        return employeeService.findAll();
    }

    @GetMapping("/getById{id}")
    public GetAllResponse getAllResponse (@PathVariable Long id){
        return employeeService.findById(id);
    }

    @Secured("ADMIN")
    @GetMapping("/getAll")
    public List<GetAllResponse> getAlls (){
        return employeeService.getAll();
    }

    @GetMapping("/getAllPagination")
    public PaginationResponse<GetAllResponse> getAllPagination (
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize){
        return employeeService.getAllPagination(pageNumber,pageSize);
    }

    @Secured("ADMIN")
    @DeleteMapping("/deleteEmployee{id}")
    public SimpleResponse deleteEmployee (@PathVariable Long id){
        return employeeService.delete(id);
    }
}
