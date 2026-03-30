package com.alten.shop.exception;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class ApiError {
  private Instant timestamp;

  private String traceId;   // pour tracer la requête (corrélation logs/APM)

  private int status;

  private String error;

  private String code;

  private String message;

  private int errorNumber;

  private String path;

  private String details;

  public ApiError(int status, int errorNumber, String error, String code, String message, String path, String details) {
    this.timestamp = Instant.now();
    this.traceId = UUID.randomUUID().toString();
    this.status = status;
    this.error = error;
    this.code = code;
    this.message = message;
    this.path = path;
    this.details = details;
    this.errorNumber = errorNumber;
  }

}
