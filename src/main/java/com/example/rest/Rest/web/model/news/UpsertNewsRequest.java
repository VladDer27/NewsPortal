package com.example.rest.Rest.web.model.news;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpsertNewsRequest {

    @NotNull
    @Size(min = 3, max = 30, message = "Min title size is: {min}. Max title size is: {max}")
    private String title;

    @NotNull
    @Size(min = 3, max = 255, message = "Min newsBody size is: {min}. Max newsBody size is: {max}")
    private String newsBody;

    @NotNull
    private Long categoryId;
}
