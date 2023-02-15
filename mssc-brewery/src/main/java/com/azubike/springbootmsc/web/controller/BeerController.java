package com.azubike.springbootmsc.web.controller;

import com.azubike.springbootmsc.web.model.BeerDto;
import com.azubike.springbootmsc.web.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Deprecated
@RestController
@RequestMapping("api/v1/beer")
@RequiredArgsConstructor
public class BeerController {
  private final BeerService beerService;

  @GetMapping(value = "/{beerId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BeerDto> getBeerDto(@PathVariable("beerId") UUID beerId) {
    return ResponseEntity.ok(beerService.getBeerById(beerId));
  }

  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BeerDto> handlePost(@RequestBody BeerDto beerDto) {
    BeerDto savedBeer = beerService.saveNewBeer(beerDto);
    final Link beerLink = linkTo(BeerController.class).slash(savedBeer.getId()).withSelfRel();
    savedBeer.add(beerLink);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedBeer);
  }

  @PutMapping(
      value = "/{beerId}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BeerDto> handleUpdate(
      @PathVariable UUID beerId, @RequestBody BeerDto beerDto) {
    BeerDto updatedBeer = beerService.updateBeer(beerId, beerDto);
    final Link link = linkTo(BeerController.class).slash(updatedBeer.getId()).withSelfRel();
    updatedBeer.add(link);
    return ResponseEntity.status(HttpStatus.OK).body(updatedBeer);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/{beerId}")
  public void handleDelete(@PathVariable UUID beerId) {
    beerService.deleteById(beerId);
  }
}
