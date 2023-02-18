package com.azubike.mssc_brewery.web.controller.aop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class MvcExceptionHandler {
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<List<String>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    // TODO Improve on Error response Message
    final List<String> errorMessages =
        ex.getBindingResult().getFieldErrors().stream()
            .map(e -> String.format("%s - %s", e.getField(), e.getDefaultMessage()))
            .collect(Collectors.toList());
    return ResponseEntity.badRequest().body(errorMessages);
  }
}
