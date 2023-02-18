package com.azubike.mssc_brewery.web.controller;

import com.azubike.mssc_brewery.web.model.CustomerDto;
import com.azubike.mssc_brewery.web.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
  public ResponseEntity<CustomerDto> handlePost(@RequestBody CustomerDto customerDto) {
    CustomerDto savedCustomer = customerService.saveNewCustomer(customerDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
  }

  @PutMapping(
      value = "/{customerId}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerDto> handleUpdate(
      @PathVariable UUID customerId, @RequestBody CustomerDto customerDto) {
    CustomerDto updatedCustomer = customerService.updateCustomer(customerId, customerDto);
    return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/{customerId}")
  public void handleDelete(@PathVariable UUID customerId) {
    customerService.deleteCustomer(customerId);
  }

  @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
  public ResponseEntity<List<String>> handleValidationError(ConstraintViolationException ex) {
    List<String> errors = new ArrayList<>(ex.getConstraintViolations().size());
    ex.getConstraintViolations()
        .forEach(
            e -> {
              errors.add(String.format("%s : %s", e.getPropertyPath().toString(), e.getMessage()));
            });
    return ResponseEntity.badRequest().body(errors);
  }
}
