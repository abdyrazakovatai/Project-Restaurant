package java15.api;

import java15.dto.request.AddChequeRequest;
import java15.dto.request.AssignChequeToMenuItemResponse;
import java15.dto.request.ChequeRequest;
import java15.dto.response.AddChequeResponse;
import java15.dto.response.GetCheckResponse;
import java15.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cheque")
@RequiredArgsConstructor
public class ChequeAPI {
    private final ChequeService chequeService;

    @PostMapping("/addCheque")
    public AddChequeResponse addCheque(@RequestBody AddChequeRequest addChequeRequest){
        return chequeService.addCheque(addChequeRequest);

    }

    @GetMapping("getCheque{id}")
    public GetCheckResponse getCheck (@PathVariable Long id){
        return chequeService.getCheque(id);
    }
}
