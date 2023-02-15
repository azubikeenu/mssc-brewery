package com.azubike.springbootmsc.web.model;

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
public class BeerDto extends RepresentationModel<BeerDto> {
  private UUID id;
  private String beerName;
  private String beerStyle;
  private Long upc;
}
