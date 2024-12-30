package java15.repo;

import java15.exception.NotFoundException;
import java15.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    Optional<Subcategory> findById(Long id);

    default Subcategory getSubcategoryById(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Subcategory with " + id + " not found"));
    }

    Optional<Subcategory> findByName(String name);

    default Subcategory getSubcategoryByName(String name) {
        return findByName(name).orElseThrow(() ->
                new NotFoundException("Subcategory with " + name + " not found"));
    }

    @Modifying
    @Query("delete from Subcategory s where s.id =:id")
    void deleteSubcategoriesById(Long id);
}
