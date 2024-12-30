package java15.service;

import jakarta.validation.Valid;
import java15.dto.request.subcategory.SubcategoryRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.subcategory.AssignResponseSubcategory;
import java15.dto.response.subcategory.SubcategoryResponse;
import org.springframework.stereotype.Service;

@Service
public interface SubcategoryService {
    AssignResponseSubcategory saveSub(Long categoryId, SubcategoryRequest subcategoryRequest);

    SimpleResponse deleteSub(Long id);

    SubcategoryResponse getSubcategory(@Valid Long id);
}
