package java15.api;

import java15.dto.request.StopListRequest;
import java15.dto.response.StopListResponse;
import java15.service.StopListService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stopList")
@RequiredArgsConstructor
public class StopListAPI {

    private final StopListService stopListService;
    @Secured("ADMIN")
    @PostMapping("/addStop")
    public StopListResponse addStopList (@RequestBody StopListRequest stopListRequest) {
        return stopListService.save(stopListRequest.getMenuItemId(),stopListRequest.getStopMessage());
    }

}