package com.azubike.mssc_brewery.validators;

import com.azubike.mssc_brewery.annotations.BeerStyleSubset;
import com.azubike.mssc_brewery.web.model.v2.BeerStyle;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class BeerStyleSubSetValidator implements ConstraintValidator<BeerStyleSubset, BeerStyle> {
  private BeerStyle[] subset;

  @Override
  public void initialize(BeerStyleSubset constraint) {
    this.subset = constraint.anyOf();
  }

  @Override
  public boolean isValid(BeerStyle value, ConstraintValidatorContext context) {
    return value == null || Arrays.asList(subset).contains(value);
  }
}
