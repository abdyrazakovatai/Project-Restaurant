package java15.service.serviceImpl;

import jakarta.transaction.Transactional;
import java15.dto.response.StopListResponse;
import java15.exception.BadRequestException;
import java15.model.MenuItem;
import java15.model.StopList;
import java15.repo.MenuItemRepository;
import java15.repo.StopListRepository;
import java15.service.StopListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class StopListServiceImpl implements StopListService {
    private final StopListRepository stopListRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    @Transactional
    public StopListResponse save(Long menuItemId, String stopMessage) {

        MenuItem menuItem = menuItemRepository.getMenuItemById(menuItemId);
        if (menuItem.getId() == null) {
            throw new BadRequestException("Invalid menu item id");
        }
        System.out.println("menuItem = " + menuItem);
        log.info("menuItem = " + menuItem);
        StopList stopList = new StopList();
        stopList.setDate(LocalDate.now());
        stopList.setReason(stopMessage);
        stopList.setMenuItem(menuItem);

        stopListRepository.save(stopList);
        log.info("stopList = " + stopList);


        return StopListResponse.builder()
                .stopListId(stopList.getId())
                .menuItemId(menuItem.getId())
                .httpStatus(HttpStatus.OK)
                .message(stopMessage)
                .build();
    }

}