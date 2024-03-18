package com.example.rest.Rest.web.model.category;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryListResponse {

    private List<CategoryResponse> categories = new ArrayList<>();

}
