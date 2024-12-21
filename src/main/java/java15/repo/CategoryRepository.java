package java15.repo;

import java15.exception.NotFoundException;
import java15.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);

    Optional<Category> findById(Long id);

    default Category getCategoryById(Long id){
        return findById(id).orElseThrow(() ->
                new NotFoundException("Category with " + id + "not found"));
    }

}