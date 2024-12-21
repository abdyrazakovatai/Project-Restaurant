package java15.api;

import java15.dto.request.MenuItemRequest;
import java15.dto.response.MenuItemResponse;
import java15.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menuItem")
@RequiredArgsConstructor
public class MenuItemAPI {
    private final MenuItemService menuItemService;

    @Secured("ADMIN")
    @PostMapping("/addMenuItem")
    public MenuItemResponse createMenu (@RequestBody MenuItemRequest menuItemRequest){
        return menuItemService.save(menuItemRequest);
    }

}