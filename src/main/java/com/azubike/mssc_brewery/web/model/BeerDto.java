package com.azubike.mssc_brewery.web.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
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
