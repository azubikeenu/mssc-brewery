package com.azubike.mssc_brewery.web.model.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDtoV2 extends RepresentationModel<BeerDtoV2> {
  private UUID id;
  private String beerName;
  private BeerStyle beerStyle;
  private Long upc;
}