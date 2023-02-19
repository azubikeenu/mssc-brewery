package com.azubike.mssc_brewery.web.model.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDtoV2 {
  @Null private UUID id;
  @NotBlank @Size(min = 2 , max = 100)
  private String beerName;
  @Null private Long version;
  @Positive @NotNull private Long upc;
  @Positive @NotNull private BigDecimal price;
  @Null private OffsetDateTime createdDate;
  @Null private OffsetDateTime lastModifiedDate;
  @NotNull private BeerStyle beerStyle;
  private Integer quantityOnHand;
  private Integer quantityToBrew;
}
