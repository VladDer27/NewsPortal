package com.example.rest.Rest.web.controller.v1;

import com.example.rest.Rest.mapper.CategoryMapper;
import com.example.rest.Rest.model.Category;
import com.example.rest.Rest.service.CategoryService;
import com.example.rest.Rest.web.model.PaginationRequest;
import com.example.rest.Rest.web.model.category.CategoryListResponse;
import com.example.rest.Rest.web.model.category.CategoryResponse;
import com.example.rest.Rest.web.model.category.UpsertCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryControllerV1 {
    private final CategoryService categoryService;
    private final CategoryMapper mapper;

    @GetMapping
    public ResponseEntity<CategoryListResponse> findAll(@Valid PaginationRequest request){
        return ResponseEntity.ok(mapper.categoryListToResponse(categoryService.findAll(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(mapper.categoryToResponse(categoryService.findById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> create(@RequestBody UpsertCategoryRequest request){
        Category newCategory = categoryService.save(mapper.requestToCategory(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.categoryToResponse(newCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody UpsertCategoryRequest request){
        Category updatedCategory = categoryService.update(mapper.requestToCategoryWithId(id, request));

        return ResponseEntity.ok().body(mapper.categoryToResponse(updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
