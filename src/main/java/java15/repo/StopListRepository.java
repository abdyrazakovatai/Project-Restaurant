package java15.repo;

import java15.model.StopList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StopListRepository extends JpaRepository<StopList, Long> {

    @Query("select count(s) > 0 from StopList s where s.menuItem.id = :id")
    boolean findStopListsById(Long id);

}
