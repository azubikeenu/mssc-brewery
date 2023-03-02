package com.azubike.mssc_brewery.annotations;

import com.azubike.mssc_brewery.validators.BeerStyleSubSetValidator;
import com.azubike.mssc_brewery.web.model.v2.BeerStyle;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = BeerStyleSubSetValidator.class)
public @interface BeerStyleSubset {
    BeerStyle[] anyOf();
    String message() default "must be any of {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
