package com.azubike.mssc_brewery.web.controller;

import com.azubike.mssc_brewery.web.model.BeerDto;
import com.azubike.mssc_brewery.web.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

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
  public ResponseEntity<BeerDto> handlePost(@Valid @RequestBody BeerDto beerDto) {
    BeerDto savedBeer = beerService.saveNewBeer(beerDto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{beerId}")
            .buildAndExpand(savedBeer.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedBeer);
  }

  @PutMapping(
      value = "/{beerId}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BeerDto> handleUpdate(
      @PathVariable UUID beerId, @Valid @RequestBody BeerDto beerDto) {
    BeerDto updatedBeer = beerService.updateBeer(beerId, beerDto);
    return ResponseEntity.status(HttpStatus.OK).body(updatedBeer);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/{beerId}")
  public void handleDelete(@PathVariable UUID beerId) {
    beerService.deleteById(beerId);
  }
}
