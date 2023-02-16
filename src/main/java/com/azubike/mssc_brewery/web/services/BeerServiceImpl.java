package com.azubike.mssc_brewery.web.services;

import com.azubike.mssc_brewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService {
  @Override
  public BeerDto getBeerById(UUID beerId) {
    return BeerDto.builder().id(UUID.randomUUID()).beerName("Star").beerStyle("Pale Ale").build();
  }

  @Override
  public BeerDto saveNewBeer(BeerDto beerDto) {
    return BeerDto.builder().id(UUID.randomUUID()).beerName(beerDto.getBeerName()).build();
  }

  @Override
  public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
    return BeerDto.builder().id(beerId).beerName(beerDto.getBeerName()).build();
  }

  @Override
  public void deleteById(UUID beerId) {
     log.debug("deleting beer with id -> {}" ,beerId );
  }
}
