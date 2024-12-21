package java15.service;

import java15.dto.request.AddChequeRequest;
import java15.dto.request.AssignChequeToMenuItemResponse;
import java15.dto.response.AddChequeResponse;
import java15.dto.response.GetCheckResponse;
import org.springframework.stereotype.Service;

@Service
public interface ChequeService {

    AddChequeResponse addCheque(AddChequeRequest addChequeRequest);

    GetCheckResponse getCheque(Long id);
}
