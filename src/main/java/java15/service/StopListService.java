package java15.service;

import java15.dto.response.stopList.StopListResponse;
import org.springframework.stereotype.Service;

@Service
public interface StopListService {
    StopListResponse save(Long menuItemId, String stopMessage);
}
