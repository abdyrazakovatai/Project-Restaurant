package java15.api;

import jakarta.validation.Valid;
import java15.dto.request.stopList.StopListRequest;
import java15.dto.response.stopList.StopListResponse;
import java15.service.StopListService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stopList")
@RequiredArgsConstructor
@Validated
public class StopListAPI {

    private final StopListService stopListService;

    @Secured({"ADMIN","WAITER"})
    @PostMapping("/addStop")
    public StopListResponse addStopList (@Valid @RequestBody StopListRequest stopListRequest) {
        return stopListService.save(stopListRequest.getMenuItemId(),stopListRequest.getStopMessage());
    }
}