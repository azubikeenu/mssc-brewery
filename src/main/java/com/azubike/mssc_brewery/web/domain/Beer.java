package com.azubike.mssc_brewery.web.domain;

import com.azubike.mssc_brewery.web.model.v2.BeerStyle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {
  private UUID id;
  private String beerName;
  private BeerStyle beerStyle;
  private Long upc;
  private Timestamp createdDate;
  private Timestamp lastModifiedDate;
}
