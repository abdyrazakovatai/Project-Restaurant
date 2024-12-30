package java15.dto.response.restaurant;

import java15.dto.response.employee.GetAllResponse;
import java15.dto.response.menuItem.MenuItemResponse;
import java15.dto.response.menuItem.MenuItemResponseWithRestaurant;

import java.util.List;

public record GetRestaurantResponse(
        Long id,
        String name,
        String location,
        List<MenuItemResponseWithRestaurant> menuItems,
        List<GetAllResponse> employees
) {
}
