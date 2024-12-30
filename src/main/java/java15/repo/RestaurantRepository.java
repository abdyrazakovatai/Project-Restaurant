package java15.repo;

import java15.exception.NotFoundException;
import java15.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findById(Long id);

    default Restaurant getRestaurantById(Long id) {
        return findById(id).orElseThrow(() ->
                new NotFoundException("Restaurant with " + id + " not found"));
    }

    boolean existsByName(String name);

    Restaurant getAllById(Long id);
}
