package java15.service.serviceImpl;

import jakarta.transaction.Transactional;
import java15.dto.request.subcategory.SubcategoryRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.subcategory.AssignResponseSubcategory;
import java15.dto.response.subcategory.SubcategoryResponse;
import java15.model.Category;
import java15.model.MenuItem;
import java15.model.Subcategory;
import java15.repo.CategoryRepository;
import java15.repo.MenuItemRepository;
import java15.repo.SubcategoryRepository;
import java15.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final MenuItemRepository menuItemRepository;
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


    @Transactional
    @Override
    public SimpleResponse deleteSub(Long id) {

        subcategoryRepository.getSubcategoryById(id);
        menuItemRepository.deleteMenuItemBySubcategory(id);
        subcategoryRepository.deleteSubcategoriesById(id);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Deleted successfully")
                .build();
    }

    @Override
    public SubcategoryResponse getSubcategory(Long id) {

        Subcategory subcategory = subcategoryRepository.getSubcategoryById(id);

        Category category = subcategory.getCategory();

        return SubcategoryResponse.builder()
                .categoryId(category.getId())
                .subcategoryId(id)
                .name(subcategory.getName())
                .httpStatus(HttpStatus.OK)
                .message("Retrieved successfully")
                .build();
    }

}
