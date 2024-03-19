package com.example.rest.Rest.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FilterValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FilterValid {

    String message() default "Поля пагинации должны быть указаны верно! pageSize должно быть больше 0, pageNumber должно быть положительным!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
