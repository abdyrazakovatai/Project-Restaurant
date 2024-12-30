package java15.service;

import jakarta.validation.Valid;
import java15.dto.request.menuItem.MenuItemRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.employee.GlobalFindResponse;
import java15.dto.response.menuItem.GetMenuItemResponse;
import java15.dto.response.menuItem.MenuItemResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuItemService {
    MenuItemResponse save(MenuItemRequest menuItemRequest);

    SimpleResponse delete(Long id);

    GetMenuItemResponse getMenuItem(Long id);

    SimpleResponse update(@Valid Long id, MenuItemRequest menuItemRequest);

    GlobalFindResponse globalFind(String keyword);

    List<GetMenuItemResponse> sortByPrice(String keyword);
}
