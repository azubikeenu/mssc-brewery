package com.azubike.mssc_brewery.web.mappers;

import com.azubike.mssc_brewery.web.domain.Beer;
import com.azubike.mssc_brewery.web.model.v2.BeerDtoV2;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
  BeerDtoV2 beerToBeerDto(Beer beer);

  Beer beerDtoToBeer(BeerDtoV2 beerDto);
}
