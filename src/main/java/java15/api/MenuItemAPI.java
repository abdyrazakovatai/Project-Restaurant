package java15.api;

import jakarta.validation.Valid;
import java15.dto.request.menuItem.MenuItemRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.menuItem.GetMenuItemResponse;
import java15.dto.response.menuItem.MenuItemResponse;
import java15.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menuItem")
@RequiredArgsConstructor
@Validated
public class MenuItemAPI {
    private final MenuItemService menuItemService;

    @Secured({"ADMIN","CHEF"})
    @PostMapping("/addMenuItem")
    public MenuItemResponse createMenu (@Valid @RequestBody MenuItemRequest menuItemRequest){
        return menuItemService.save(menuItemRequest);
    }

    @PutMapping("/updateMenItem{id}")
    public SimpleResponse updateMenuItem (@Valid @PathVariable Long id, MenuItemRequest menuItemRequest){
        return menuItemService.update(id,menuItemRequest);
    }

    @GetMapping("/getMenuItem{id}")
    public GetMenuItemResponse getMenuItem (@PathVariable Long id){
        return menuItemService.getMenuItem(id);
    }

    @Secured("{ADMIN,CHEF")
    @DeleteMapping("/deleteMenuItem{id}")
    public SimpleResponse deleteMenuItem (@PathVariable Long id){
        return menuItemService.delete(id);
    }
}