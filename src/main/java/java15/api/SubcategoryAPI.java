package java15.api;

import java15.dto.request.SubcategoryRequest;
import java15.dto.response.AssignResponseSubcategory;
import java15.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subcategory")
@RequiredArgsConstructor
public class SubcategoryAPI {

    private final SubcategoryService subcategoryService;
    @Secured("ADMIN")
    @PostMapping("/saveSub")
    public AssignResponseSubcategory assignResponseSubcategory (@RequestBody SubcategoryRequest subcategoryRequest) {
        return subcategoryService.saveSub(subcategoryRequest.getCategoryId(),subcategoryRequest);
    }
}
