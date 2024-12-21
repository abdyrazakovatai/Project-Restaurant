package java15.api;

import java15.dto.request.CategoryRequest;
import java15.dto.response.CategorySaveResponse;
import java15.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryAPI {
    private final CategoryService categoryService;


    @PostMapping("/addCategory")
    public CategorySaveResponse addCategory (@RequestBody CategoryRequest categoryRequest) {
        return categoryService.save(categoryRequest);
    }
}
