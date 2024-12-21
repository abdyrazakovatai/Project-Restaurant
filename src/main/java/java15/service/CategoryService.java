package java15.service;

import java15.dto.request.CategoryRequest;
import java15.dto.response.CategorySaveResponse;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    CategorySaveResponse save(CategoryRequest categoryRequest);
}
