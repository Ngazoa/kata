package com.alten.shop.exception;

import lombok.Getter;

@Getter
public class AlternCataException extends RuntimeException {
  private final ErrorCode code;

  private Integer errorNumber;

  public AlternCataException(ErrorCode code, Integer errorNumber, String message) {
    super(message);
    this.code = code;
    this.errorNumber = errorNumber;
  }

  public AlternCataException(ErrorCode code, String message) {
    super(message);
    this.code = code;
  }

}
