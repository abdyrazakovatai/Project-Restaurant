package java15.repo;

import java15.exception.NotFoundException;
import java15.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    Optional<MenuItem> findById(Long id);

    default MenuItem getMenuItemById(Long id){
        return findById(id).orElseThrow(()->
                new NotFoundException("MenuItem with not found"));
    }
}
