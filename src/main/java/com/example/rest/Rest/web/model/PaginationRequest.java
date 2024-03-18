package com.example.rest.Rest.web.model;

import com.example.rest.Rest.validation.FilterValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FilterValid
public class PaginationRequest {

    private Integer pageSize = 10;


    private Integer pageNumber = 0;

}
