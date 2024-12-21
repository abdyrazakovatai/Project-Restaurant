package java15.api;

import java15.dto.request.AcceptOrRejectRequest;
import java15.dto.request.BidEmployeeRequest;
import java15.dto.request.SaveEmployeeRequest;
import java15.dto.response.BidAcceptOrRejectResponse;
import java15.dto.response.BidEmployeeResponse;
import java15.dto.response.GetAllBidEmployeeResponse;
import java15.dto.response.SaveEmployeeResponse;
import java15.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor

public class EmployeeAPI {
    private final EmployeeService employeeService;

    //save from Admin. EMPLOYEE  // ROLE CHER WAITER restaurant id

    @Secured("ADMIN")
    @PostMapping("/saveEmployee")
    public SaveEmployeeResponse saveEmployee(@RequestBody SaveEmployeeRequest registerRequest) {
        return employeeService.saveEmployee(registerRequest);
    }

    //bid from employee// EMPLOYEE ROLE

    @PostMapping("/bidEmployee")
    public BidEmployeeResponse bidEmployee(@RequestBody BidEmployeeRequest bidEmployeeRequest){
        return employeeService.bidEmployee(bidEmployeeRequest);
    }

    // accept or reject / employeeId / ACCEPT / restaurant id  or REJECT/ delete employee(zaiavka tashtagan)restaurants

    @PostMapping("/acceptOrReject")
    public BidAcceptOrRejectResponse acceptOrReject (@RequestBody AcceptOrRejectRequest acceptOrRejectRequest){
        return employeeService.bidAcceptOrReject(acceptOrRejectRequest.getEmployeeId(),acceptOrRejectRequest.getRestaurantId(),acceptOrRejectRequest.getBid());
    }

    // find by id

    //get all employee(bid)
    @GetMapping("/getAllEmployee")
    public List<GetAllBidEmployeeResponse> getAll (){
        return employeeService.findAll();
    }


//    @Secured("ADMIN")
//    @PostMapping("/assignChef")
//    public AssignResponse assignChef (@RequestBody AssignRequest assignRequest) {
//        return employeeService.assignChef(assignRequest.getEmployeeId(), assignRequest.getRestaurantId());
//    }
//    @Secured("ADMIN")
//    @PostMapping("/assignWaiter")
//    public AssignResponse assignWaiter (@RequestBody AssignRequest assignRequest) {
//        return employeeService.assignWaiter(assignRequest.getEmployeeId(), assignRequest.getRestaurantId());
//    }
}
