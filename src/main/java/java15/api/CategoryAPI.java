package java15.api;

import jakarta.validation.Valid;
import java15.dto.request.category.CategoryRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.category.CategorySaveResponse;
import java15.dto.response.category.GetCategoryResponse;
import java15.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Validated
public class CategoryAPI {
    private final CategoryService categoryService;

    @Secured({"ADMIN","CHEF"})
    @PostMapping("/addCategory")
    public CategorySaveResponse addCategory (@Valid @RequestBody CategoryRequest categoryRequest) {
        return categoryService.save(categoryRequest);
    }

    @GetMapping("/getCategory{id}")
    public GetCategoryResponse getCategoryResponse (@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    @Secured("ADMIN")
    @DeleteMapping("/deleteCategory{id}")
    public SimpleResponse deleteCategory (@PathVariable Long id) {
        return categoryService.delete(id);
    }
}
