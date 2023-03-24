package com.azubike.mssc_brewery.web.model.v2;

import com.azubike.mssc_brewery.annotations.BeerStyleValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;import com.fasterxml.jackson.databind.ObjectWriter;import com.fasterxml.jackson.databind.PropertyNamingStrategy;import lombok.AllArgsConstructor;
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

  @NotBlank
  @Size(min = 2, max = 100)
  private String beerName;


  @Null private Long version;
  @Positive @NotNull private Long upc;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @Positive
  @NotNull
  private BigDecimal price;

  //  @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
  @Null private OffsetDateTime createdDate;

  @Null private OffsetDateTime lastModifiedDate;

  @NotNull @BeerStyleValue private BeerStyle beerStyle;

  @Positive private Integer quantityOnHand;
  @Positive private Integer quantityToBrew;


}
