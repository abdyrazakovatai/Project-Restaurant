package java15.service.serviceImpl;

import java15.dto.request.MenuItemRequest;
import java15.dto.response.MenuItemResponse;
import java15.model.MenuItem;
import java15.model.Restaurant;
import java15.model.Subcategory;
import java15.repo.MenuItemRepository;
import java15.repo.RestaurantRepository;
import java15.repo.SubcategoryRepository;
import java15.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final SubcategoryRepository subcategoryRepository;


    @Override
    public MenuItemResponse save(MenuItemRequest menuItemRequest) {
        Restaurant restaurant = restaurantRepository.getRestaurantById(menuItemRequest.getRestaurantId());

        Subcategory subcategory = subcategoryRepository.getSubcategoryById(menuItemRequest.getSubcategoryId());

        MenuItem saveMenuItem = menuItemRepository.save(
                MenuItem.builder()
                        .restaurant(restaurant)
                        .subcategory(subcategory)
                        .name(menuItemRequest.getName())
                        .image(menuItemRequest.getImage())
                        .price(menuItemRequest.getPrice())
                        .isVegetarian(menuItemRequest.isVegetarian())
                        .description(menuItemRequest.getDescription())
                        .build());

        if (restaurant.getMenuItems() == null) {
            restaurant.setMenuItems(new ArrayList<>());
        }
        restaurant.getMenuItems().add(saveMenuItem);
        return MenuItemResponse.builder()
                .restaurantId(saveMenuItem.getRestaurant().getId())
                .menuId(saveMenuItem.getId())
                .httpStatus(HttpStatus.OK)
                .message("MenuItem saved successfully")
                .build();
    }
}