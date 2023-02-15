package com.azubike.springbootmsc.web.services.v2;

import com.azubike.springbootmsc.web.model.v2.BeerDtoV2;

import java.util.UUID;

public interface BeerServiceV2 {
  BeerDtoV2 getBeerById(UUID beerId);

  BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto);

  BeerDtoV2 updateBeer(UUID beerId, BeerDtoV2 beerDto);

  void deleteById(UUID beerId);
}
