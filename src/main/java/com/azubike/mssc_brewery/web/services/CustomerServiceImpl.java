package com.azubike.mssc_brewery.web.services;

import com.azubike.mssc_brewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
  @Override
  public CustomerDto getById(UUID customerId) {
    return CustomerDto.builder().id(UUID.randomUUID()).name("Richard").build();
  }

  @Override
  public CustomerDto saveNewCustomer(CustomerDto customerDto) {
    return CustomerDto.builder().id(UUID.randomUUID()).name(customerDto.getName()).build();
  }

  @Override
  public CustomerDto updateCustomer(UUID customerId, CustomerDto customerDto) {
    return CustomerDto.builder().id(customerId).name(customerDto.getName()).build();
  }

  @Override
  public void deleteCustomer(UUID customerId) {

  }
}
