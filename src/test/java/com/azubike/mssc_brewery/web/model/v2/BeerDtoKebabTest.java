package com.azubike.mssc_brewery.web.model.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;
import util.TestUtils;

@JsonTest
@ActiveProfiles("kebab")
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")

public class BeerDtoKebabTest {
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testKebabCasing() throws Exception {
        BeerDtoV2 beerDtoV2 = TestUtils.createValidBeerDto();
        String beerJson = objectMapper.writeValueAsString(beerDtoV2);
        System.out.println(beerJson);
    }
}
