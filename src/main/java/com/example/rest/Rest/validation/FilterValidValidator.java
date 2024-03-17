package com.example.rest.Rest.validation;

import com.example.rest.Rest.web.model.PaginationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;


public class FilterValidValidator implements ConstraintValidator<FilterValid, PaginationRequest> {
    @Override
    public boolean isValid(PaginationRequest request, ConstraintValidatorContext constraintValidatorContext) {
        return !ObjectUtils.anyNull(request.getPageNumber(), request.getPageSize());
    }
}
