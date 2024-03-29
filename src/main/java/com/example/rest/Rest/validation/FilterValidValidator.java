package com.example.rest.Rest.validation;

import com.example.rest.Rest.web.model.PaginationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;


public class FilterValidValidator implements ConstraintValidator<FilterValid, PaginationRequest> {
    @Override
    public boolean isValid(PaginationRequest request, ConstraintValidatorContext constraintValidatorContext) {
        return !ObjectUtils.anyNull(request, request.getPageNumber(), request.getPageSize()) && request.getPageSize() >= 1
                && request.getPageNumber() >= 0;
    }
}
