package com.azubike.mssc_brewery.web.controller.v2;

import com.azubike.mssc_brewery.web.model.v2.BeerDtoV2;
import com.azubike.mssc_brewery.web.services.v2.BeerServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/beer")
public class BeerControllerV2 {

  private final BeerServiceV2 beerService;

  @GetMapping(value = "/{beerId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BeerDtoV2> getBeerDtoV2(@PathVariable("beerId") UUID beerId) {
    return ResponseEntity.ok(beerService.getBeerById(beerId));
  }

  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BeerDtoV2> handlePost(@RequestBody BeerDtoV2 beerDto) {
    BeerDtoV2 savedBeer = beerService.saveNewBeer(beerDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedBeer);
  }

  @PutMapping(
      value = "/{beerId}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BeerDtoV2> handleUpdate(
      @PathVariable UUID beerId, @RequestBody BeerDtoV2 beerDto) {
    BeerDtoV2 updatedBeer = beerService.updateBeer(beerId, beerDto);
    return ResponseEntity.status(HttpStatus.OK).body(updatedBeer);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/{beerId}")
  public void handleDelete(@PathVariable UUID beerId) {
    beerService.deleteById(beerId);
  }
}
