package com.azubike.mssc_brewery.web.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorMessage {
  private Date timeStamp;
  private String message;
  private int status;
  private String path ;
}
