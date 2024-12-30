package java15.service;

import java15.dto.request.cheque.AddChequeRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.cheque.AddChequeResponse;
import java15.dto.response.cheque.GetCheckResponse;
import org.springframework.stereotype.Service;

@Service
public interface ChequeService {

    AddChequeResponse addCheque(AddChequeRequest addChequeRequest);

    GetCheckResponse getCheque(Long id);

    SimpleResponse delete(Long id);

}
