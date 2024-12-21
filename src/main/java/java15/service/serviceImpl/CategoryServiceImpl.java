package java15.service.serviceImpl;

import java15.dto.request.CategoryRequest;
import java15.dto.response.CategorySaveResponse;
import java15.exception.BadRequestException;
import java15.model.Category;
import java15.repo.CategoryRepository;
import java15.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategorySaveResponse save(CategoryRequest categoryRequest) {
        if(categoryRepository.existsByName(categoryRequest.getName())){
            throw new  BadRequestException("Category with name " + categoryRequest.getName() + " already exists ");
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
}
