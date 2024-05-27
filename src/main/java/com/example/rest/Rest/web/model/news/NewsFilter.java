package com.example.rest.Rest.web.model.news;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsFilter {

    @Positive(message = "pageSize должно быть положительным!")
    private Integer pageSize = 10;

    @PositiveOrZero(message = "pageNumber должно быть положительным или больше нуля!")
    private Integer pageNumber = 0;

    private Long userId;

    private Long categoryId;
}
