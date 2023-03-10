package com.azubike.mssc_brewery.web.controller;

import com.azubike.mssc_brewery.web.model.BeerDto;
import com.azubike.mssc_brewery.web.services.BeerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
class BeerControllerTest {
  @MockBean BeerService beerService;
  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;
  BeerDto beerDto;
  BeerDto validBeer;

  @BeforeEach
  void setup() {
    beerDto = BeerDto.builder().beerStyle("PALE_ALE").beerName("notNull").upc(10000L).build();
    validBeer =
        BeerDto.builder().id(UUID.randomUUID()).beerName("DummyBeer").beerStyle("PISLSNER").build();
  }

  @Test
  void getBeerById() throws Exception {
    mockMvc
        .perform(
            get("/api/v1/beer/{beerId}", UUID.randomUUID())
                .param("Hello", "World")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void saveNewBeer() throws Exception {
    String beerDtoJson = objectMapper.writeValueAsString(beerDto);
    when(beerService.saveNewBeer(any(BeerDto.class))).thenReturn(validBeer);
    mockMvc
        .perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
        .andExpect(status().isCreated());
  }

  @Test
  void updateBeerById() throws Exception {
    String beerDtoJson = objectMapper.writeValueAsString(beerDto);
    mockMvc
        .perform(
            put("/api/v1/beer/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
        .andExpect(status().isOk());
  }
}
