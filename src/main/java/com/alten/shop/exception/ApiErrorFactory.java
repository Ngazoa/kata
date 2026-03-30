package com.alten.shop.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

public class ApiErrorFactory {

  public static ApiError build(HttpStatus status, int errorNumber, ErrorCode code, String
    message, HttpServletRequest request, String details) {
    return new ApiError(
      status.value(),
      errorNumber,
      status.getReasonPhrase(),
      code.name(),
      message,
      request.getRequestURI(),
      details
    );
  }

  public static ApiError build(HttpStatus status, int errorNumber, ErrorCode code,
                               String message, HttpServletRequest request) {
    return build(status, errorNumber, code, message, request, null);
  }

}
