package com.azubike.mssc_brewery.validators;

import com.azubike.mssc_brewery.annotations.BeerStyleValue;
import com.azubike.mssc_brewery.web.model.v2.BeerStyle;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BeerStyleValueValidator implements ConstraintValidator<BeerStyleValue, BeerStyle> {
  @Override
  public boolean isValid(BeerStyle style, ConstraintValidatorContext context) {
    if (style == null) {
      // Null values are handled by the @NotNull annotation
      return true;
    }

    for (BeerStyle validStyle : BeerStyle.values()) {
      if (style.equals(validStyle)) {
        return true;
      }
    }

    return false;
  }
}
