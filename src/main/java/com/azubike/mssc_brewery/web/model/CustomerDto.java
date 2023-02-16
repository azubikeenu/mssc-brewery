package com.azubike.mssc_brewery.web.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto extends RepresentationModel<CustomerDto> {
  private UUID id;
  private String name;
}
