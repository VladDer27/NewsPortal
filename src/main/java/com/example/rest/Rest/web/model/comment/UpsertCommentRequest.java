package com.example.rest.Rest.web.model.comment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpsertCommentRequest {

    @NotNull
    private Long newsId;

    @NotNull
    @Size(min = 1, max = 255, message = "Min comment size is: {min}. Max comment size is: {max}")
    private String comment;
}
