package com.example.rest.Rest.mapper;

import com.example.rest.Rest.model.Category;
import com.example.rest.Rest.web.model.category.CategoryListResponse;
import com.example.rest.Rest.web.model.category.CategoryResponse;
import com.example.rest.Rest.web.model.category.UpsertCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryMapper {
    Category requestToCategory(UpsertCategoryRequest request);

    @Mapping(source = "categoryId", target = "id")
    Category requestToCategoryWithId(Long categoryId, UpsertCategoryRequest request);

    CategoryResponse categoryToResponse(Category category);

    default CategoryListResponse categoryListToResponse(List<Category> categories){
        CategoryListResponse response = new CategoryListResponse();

        response.setCategories(categories.stream().map(this::categoryToResponse).collect(Collectors.toList()));

        return response;
    }
}
