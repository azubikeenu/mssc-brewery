package com.azubike.springbootmsc.web.controller;

import com.azubike.springbootmsc.web.model.CustomerDto;
import com.azubike.springbootmsc.web.services.CustomerService;
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
@RequestMapping("api/v1/customer")
public class CustomerController {
  private final CustomerService customerService;

  @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID customerId) {
    return ResponseEntity.ok(customerService.getById(customerId));
  }

  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
 public  ResponseEntity<CustomerDto> handlePost(@RequestBody CustomerDto customerDto) {
    CustomerDto savedCustomer = customerService.saveNewCustomer(customerDto);
    final Link customerLink =
        linkTo(CustomerController.class).slash(savedCustomer.getId()).withSelfRel();
    savedCustomer.add(customerLink);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
  }

  @PutMapping(
      value = "/{customerId}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerDto> handleUpdate(
      @PathVariable UUID customerId, @RequestBody CustomerDto customerDto) {
    CustomerDto updatedCustomer = customerService.updateCustomer(customerId, customerDto);
    final Link link = linkTo(CustomerController.class).slash(updatedCustomer.getId()).withSelfRel();
    updatedCustomer.add(link);
    return  ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
  }


  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/{customerId}")
  public void handleDelete(@PathVariable UUID customerId){
    customerService.deleteCustomer(customerId);
  }
}
