package com.azubike.mssc_brewery.web.model.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import util.TestUtils;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class BeerDtoV2Test {
  @Autowired ObjectMapper objectMapper;

  @Test
  void testSerializeDto() throws Exception {
    BeerDtoV2 beerDto = TestUtils.createValidBeerDto();
    String jsonString = objectMapper.writeValueAsString(beerDto);
    System.out.println(jsonString);
    assertThat(jsonString).isNotNull();
  }

  @Test
  void testDeserializeDto() throws IOException {
    String jsonString =
        "{\"id\":\"a5e68e6b-36cb-4291-b2db-82af18654a95\",\"beerName\":\"PALE_ALE\",\"version\":1,\"upc\":1230300,\"price\":10.5,\"createdDate\":\"2023-02-19T17:00:14.3024917+01:00\",\"lastModifiedDate\":\"2023-02-19T17:00:14.3024917+01:00\",\"beerStyle\":\"ALE\",\"quantityOnHand\":10,\"quantityToBrew\":10}";
    BeerDtoV2 beerDto = objectMapper.readValue(jsonString, BeerDtoV2.class);
    assertThat(beerDto.getId()).isNotNull();
  }
}
