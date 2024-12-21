package java15.service;

import java15.dto.request.MenuItemRequest;
import java15.dto.response.MenuItemResponse;
import org.springframework.stereotype.Service;

@Service
public interface MenuItemService {
    MenuItemResponse save(MenuItemRequest menuItemRequest);

}
