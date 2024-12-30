package java15.api;

import jakarta.validation.Valid;
import java15.dto.request.cheque.AddChequeRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.cheque.AddChequeResponse;
import java15.dto.response.cheque.GetCheckResponse;
import java15.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cheque")
@RequiredArgsConstructor
@Validated
public class ChequeAPI {
    private final ChequeService chequeService;

    @Secured({"ADMIN","WAITER"})
    @PostMapping("/addCheque")
    public AddChequeResponse addCheque(@Valid @RequestBody AddChequeRequest addChequeRequest){
        return chequeService.addCheque(addChequeRequest);
    }

    @Secured({"ADMIN","WAITER"})
    @GetMapping("/getCheque{id}")
    public GetCheckResponse getCheck (@Valid @PathVariable Long id){
        return chequeService.getCheque(id);
    }

    @Secured({"ADMIN","WAITER"})
    @DeleteMapping("/deleteCheque{id}")
    public SimpleResponse deleteCheque(@PathVariable Long id){
        return chequeService.delete(id);
    }
}
