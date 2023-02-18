package com.azubike.mssc_brewery.web.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto{
  private UUID id;
  private String name;
}
