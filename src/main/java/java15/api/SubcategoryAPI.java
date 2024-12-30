package java15.api;

import jakarta.validation.Valid;
import java15.dto.request.subcategory.SubcategoryRequest;
import java15.dto.response.auth.SimpleResponse;
import java15.dto.response.subcategory.AssignResponseSubcategory;
import java15.dto.response.subcategory.SubcategoryResponse;
import java15.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subcategory")
@RequiredArgsConstructor
@Validated
public class SubcategoryAPI {

    private final SubcategoryService subcategoryService;

    @Secured({"ADMIN","CHEF"})
    @PostMapping("/saveSub")
    public AssignResponseSubcategory assignResponseSubcategory (@Valid @RequestBody SubcategoryRequest subcategoryRequest) {
        return subcategoryService.saveSub(subcategoryRequest.getCategoryId(),subcategoryRequest);
    }

    @GetMapping("getSubcategory{id}")
    public SubcategoryResponse getSubcategory (@PathVariable Long id) {
        return subcategoryService.getSubcategory(id);
    }

    @Secured("ADMIN")
    @DeleteMapping("/deleteSub{id}")
    public SimpleResponse deleteSubcategory (@PathVariable Long id) {
        return subcategoryService.deleteSub(id);
    }
}
