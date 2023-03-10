package com.azubike.mssc_brewery.web.services.v2;

import com.azubike.mssc_brewery.web.model.v2.BeerDtoV2;
import com.azubike.mssc_brewery.web.model.v2.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
  @Override
  public BeerDtoV2 getBeerById(UUID beerId) {
    return BeerDtoV2.builder()
        .id(UUID.randomUUID())
        .beerName("Star")
        .beerStyle(BeerStyle.ALE)
        .build();
  }

  @Override
  public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto) {
    return BeerDtoV2.builder().id(UUID.randomUUID()).beerName(beerDto.getBeerName()).beerStyle(BeerStyle.ALE).build();
  }

  @Override
  public BeerDtoV2 updateBeer(UUID beerId, BeerDtoV2 beerDto) {
    return BeerDtoV2.builder().id(beerId).beerName(beerDto.getBeerName()).build();
  }

  @Override
  public void deleteById(UUID beerId) {
    log.debug("deleting beer with id -> {}", beerId);
  }
}
