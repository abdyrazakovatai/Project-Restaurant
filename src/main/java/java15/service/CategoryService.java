package java15.service;

import java15.dto.request.category.CategoryRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.category.CategorySaveResponse;
import java15.dto.response.category.GetCategoryResponse;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    CategorySaveResponse save(CategoryRequest categoryRequest);

    SimpleResponse delete(Long id);

    GetCategoryResponse getCategory(Long id);
}
