package com.azubike.mssc_brewery.annotations;

import com.azubike.mssc_brewery.validators.BeerStyleValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BeerStyleValueValidator.class)
public @interface BeerStyleValue {
  String message() default "Invalid beer style";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
