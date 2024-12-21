package java15.service;

import java15.dto.request.SubcategoryRequest;
import java15.dto.response.AssignResponseSubcategory;
import org.springframework.stereotype.Service;

@Service
public interface SubcategoryService {
    AssignResponseSubcategory saveSub(Long categoryId, SubcategoryRequest subcategoryRequest);
}
