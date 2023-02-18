package com.azubike.mssc_brewery.web.mappers;

import com.azubike.mssc_brewery.web.domain.Customer;
import com.azubike.mssc_brewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
  CustomerDto customerToDto(Customer customer);

  Customer dtoToCustomer(CustomerDto customerDto);
}
