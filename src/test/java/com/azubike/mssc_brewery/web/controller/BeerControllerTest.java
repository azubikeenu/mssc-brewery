package com.azubike.mssc_brewery.web.controller;

import com.azubike.mssc_brewery.web.model.BeerDto;
import com.azubike.mssc_brewery.web.services.BeerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- This was commented to integrate restDocs---------//
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
class BeerControllerTest {
  @MockBean BeerService beerService;
  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;
  BeerDto beerDto;

  @BeforeEach
  void setup() {
    beerDto = BeerDto.builder().beerStyle("PALE_ALE").beerName("notNull").upc(10000L).build();
  }

  @Test
  void getBeerById() throws Exception {

    mockMvc
        .perform(get("/api/v1/beer/{beerId}", UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "v1/beer",
                pathParameters(
                    parameterWithName("beerId").description("UUID of desired beer to get"))));
  }

  @Test
  void saveNewBeer() throws Exception {
    String beerDtoJson = objectMapper.writeValueAsString(beerDto);
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
