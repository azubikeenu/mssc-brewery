package util;

import com.azubike.mssc_brewery.web.model.v2.BeerDtoV2;
import com.azubike.mssc_brewery.web.model.v2.BeerStyle;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class TestUtils {
  public static BeerDtoV2 createValidBeerDto() {
    return BeerDtoV2.builder()
        .id(UUID.randomUUID())
        .beerName("PALE_ALE")
        .version(1L)
        .beerStyle(BeerStyle.ALE)
        .price(new BigDecimal("10.5"))
        .upc(1230300L)
        .createdDate(OffsetDateTime.now())
        .lastModifiedDate(OffsetDateTime.now())
        .quantityToBrew(10)
        .quantityOnHand(10)
        .build();
  }

  public static BeerDtoV2 createBeerDto() {
    return BeerDtoV2.builder()
        .beerName("PALE_ALE")
        .beerStyle(BeerStyle.ALE)
        .price(new BigDecimal("10.5"))
        .upc(1230300L)
        .quantityToBrew(10)
        .quantityOnHand(10)
        .build();
  }
}
