package com.azubike.mssc_brewery.web.controller.v2;

import com.azubike.mssc_brewery.web.model.v2.BeerDtoV2;
import com.azubike.mssc_brewery.web.services.v2.BeerServiceV2;
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
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;
import util.TestUtils;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "https" , uriHost = "richard.com.ellipsis" , uriPort = 80)
@WebMvcTest(BeerControllerV2.class)
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
class BeerControllerV2Test {
  @MockBean BeerServiceV2 beerServiceV2;
  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;
  BeerDtoV2 beerDto;

  @BeforeEach
  void setUp() {
    beerDto = TestUtils.createValidBeerDto();
  }

  @Test
  void getBeerDtoV2() throws Exception {
    when(beerServiceV2.getBeerById(any())).thenReturn(beerDto);

    mockMvc
        .perform(
            get("/api/v2/beer/{beerId}", UUID.randomUUID())
                .param("Hello", "World")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "v2/beer-get",
                pathParameters(
                    parameterWithName("beerId").description("UUID of desired beer to get")),
                requestParameters(
                    parameterWithName("Hello").description("Just a test Query param")),
                responseFields(
                    fieldWithPath("id").description("Id of the beer"),
                    fieldWithPath("beerName").description("Name of beer"),
                    fieldWithPath("upc").description("Upc of beer"),
                    fieldWithPath("version").description("version of beer"),
                    fieldWithPath("createdDate").description("Date of creation"),
                    fieldWithPath("lastModifiedDate").description("Last Updated Date"),
                    fieldWithPath("beerStyle").description("Style of beerDto"),
                    fieldWithPath("price").description("Price of beerDto"),
                    fieldWithPath("quantityToBrew").description("Quantity of beer to brew"),
                    fieldWithPath("quantityOnHand").description("Quantity of beer at hand"))));
  }

  @Test
  void handlePost() throws Exception {
    BeerDtoV2 createBeerDto = TestUtils.createBeerDto();
    String beerDtoToJson = objectMapper.writeValueAsString(createBeerDto);
    when(beerServiceV2.saveNewBeer(any(BeerDtoV2.class)))
        .thenReturn(TestUtils.createValidBeerDto());

    ConstrainedFields fields = new ConstrainedFields(BeerDtoV2.class);

    mockMvc
        .perform(
            post("/api/v2/beer").contentType(MediaType.APPLICATION_JSON).content(beerDtoToJson))
        .andExpect(status().isCreated())
        .andDo(
            document(
                "v2/beer-new",
                requestFields(
                    fields.withPath("beerName").description("name of the beer"),
                    fields.withPath("id").ignored(),
                    fields.withPath("upc").description("Upc of beer").attributes(),
                    fields.withPath("createdDate").ignored(),
                    fields.withPath("lastModifiedDate").ignored(),
                    fields.withPath("version").ignored(),
                    fields.withPath("beerStyle").description("Style of beerDto"),
                    fields.withPath("price").description("Price of beerDto"),
                    fields.withPath("quantityToBrew").description("Quantity of beer to brew"),
                    fields.withPath("quantityOnHand").ignored()),
                responseFields(
                    fieldWithPath("id").description("Id of the beer"),
                    fieldWithPath("beerName").description("Name of beer"),
                    fieldWithPath("upc").description("Upc of beer"),
                    fieldWithPath("version").description("version of beer"),
                    fieldWithPath("createdDate").description("Date of creation"),
                    fieldWithPath("lastModifiedDate").description("Last Updated Date"),
                    fieldWithPath("beerStyle").description("Style of beerDto"),
                    fieldWithPath("price").description("Price of beerDto"),
                    fieldWithPath("quantityToBrew").description("Quantity of beer to brew"),
                    fieldWithPath("quantityOnHand").description("Quantity of beer at hand"))));
  }

  @Test
  void handleUpdate() {}

  @Test
  void handleDelete() {}



  private static class ConstrainedFields {
    private final ConstraintDescriptions constraintDescriptions;

    ConstrainedFields(Class<?> input) {
      this.constraintDescriptions = new ConstraintDescriptions(input);
    }

    private FieldDescriptor withPath(String path) {
      return fieldWithPath(path)
          .attributes(
              key("constraints")
                  .value(
                      StringUtils.collectionToDelimitedString(
                          this.constraintDescriptions.descriptionsForProperty(path), ". ")));
    }
  }
}
