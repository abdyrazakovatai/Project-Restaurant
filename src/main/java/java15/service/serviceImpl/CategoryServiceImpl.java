package java15.service.serviceImpl;

import jakarta.transaction.Transactional;
import java15.dto.request.category.CategoryRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.category.CategorySaveResponse;
import java15.dto.response.category.GetCategoryResponse;
import java15.exception.BadRequestException;
import java15.model.Category;
import java15.model.Subcategory;
import java15.repo.CategoryRepository;
import java15.repo.MenuItemRepository;
import java15.repo.SubcategoryRepository;
import java15.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final MenuItemRepository menuItemRepository;


    @Override
    public CategorySaveResponse save(CategoryRequest categoryRequest) {
        if (categoryRepository.existsByName(categoryRequest.getName())) {
            throw new BadRequestException("Category with name " + categoryRequest.getName() + " already exists ");
        }
        Category saveCategory = categoryRepository.save(
                Category.builder()
                        .name(categoryRequest.getName())
                        .build());
        return CategorySaveResponse.builder()
                .categoryId(saveCategory.getId())
                .categoryName(saveCategory.getName())
                .httpStatus(HttpStatus.OK)
                .message("Category saved")
                .build();
    }

    @Transactional
    @Override
    public SimpleResponse delete(Long id) {

        Category category = categoryRepository.getCategoryById(id);

        Hibernate.initialize(category.getSubcategories());
        category.getSubcategories().forEach(subcategory -> {
            Hibernate.initialize(subcategory.getMenuItems());
        });

        category.getSubcategories().forEach(subcategory -> {
            subcategory.getMenuItems().forEach(menuItem -> menuItem.setSubcategory(null));
            menuItemRepository.deleteAll(subcategory.getMenuItems());
        });

        category.getSubcategories().forEach(subcategory -> subcategory.setCategory(null));
        subcategoryRepository.deleteAll(category.getSubcategories());

        categoryRepository.delete(category);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Category deleted")
                .build();
    }

    @Override
    public GetCategoryResponse getCategory(Long id) {
        Category category = categoryRepository.getCategoryById(id);

        if (category == null) {
            return GetCategoryResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("Category not found")
                    .build();
        }
        return GetCategoryResponse.builder()
                .id(id)
                .name(category.getName())
                .httpStatus(HttpStatus.OK)
                .message("Category found")
                .build();
    }
}
