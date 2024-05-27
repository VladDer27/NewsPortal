package com.example.rest.Rest.web.model.category;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpsertCategoryRequest {

    @Size(min = 3, max = 30, message = "Min categoryName size is: {min}. Max categoryName size is: {max}")
    private String name;
}
