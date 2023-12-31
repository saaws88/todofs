package com.nekot.todoBack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.nekot.todoBack.dto.ErrorDto;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice(annotations = RestController.class)
@Log4j2
class GlobalControllerExceptionHandler {
  
  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ErrorDto> handleResponseStatusException(ResponseStatusException e) {
    log.error("response status exception", e);
    ErrorDto errorDto = new ErrorDto(e.getMessage());
    return new ResponseEntity<>(errorDto, e.getStatusCode());
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorDto> handleRuntimeException(RuntimeException e) {
    log.error("internal server error", e);
    ErrorDto errorDto = new ErrorDto("internal server error");
    return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
