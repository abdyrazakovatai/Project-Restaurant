package java15.service.serviceImpl;

import jakarta.transaction.Transactional;
import java15.dto.request.SubcategoryRequest;
import java15.dto.response.AssignResponseSubcategory;
import java15.model.Category;
import java15.model.Subcategory;
import java15.repo.CategoryRepository;
import java15.repo.SubcategoryRepository;
import java15.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public AssignResponseSubcategory saveSub(Long categoryId, SubcategoryRequest subcategoryRequest) {
        Category category = categoryRepository.getCategoryById(categoryId);

        Hibernate.initialize(category.getSubcategories());

        Subcategory subcategory = Subcategory.builder()
                .name(subcategoryRequest.getName())
                .category(category)
                .build();

        category.getSubcategories().add(subcategory);
        subcategory.setCategory(category);

        subcategoryRepository.save(subcategory);

        return AssignResponseSubcategory.builder()
                .categoryId(categoryId)
                .subcategoryId(subcategory.getId())
                .httpStatus(HttpStatus.OK)
                .message("Assigned successfully")
                .build();
    }
}
