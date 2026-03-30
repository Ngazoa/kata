package com.alten.shop.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  private ResponseEntity<ApiError> buildResponse(
          ErrorCode code,
          String message,
          HttpServletRequest request,
          String details
  ) {
    ApiError error = ApiErrorFactory.build(
            code.getHttpStatus(),
            code.getNumber(),
            code,
            message,
            request,
            details
    );
    return ResponseEntity.status(code.getHttpStatus()).body(error);
  }

  @ExceptionHandler(AlternCataException.class)
  public ResponseEntity<ApiError> handleBusiness(AlternCataException ex, HttpServletRequest request) {
    log.error("Erreur  Carbone vert ", ex);
    return buildResponse(ex.getCode(), ex.getMessage(), request, Arrays.toString(ex.getStackTrace()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
    String details = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(err -> err.getField() + " " + err.getDefaultMessage())
            .collect(Collectors.joining(", "));

    return buildResponse(ErrorCode.VALIDATION_ERROR, "Erreur de validation des champs.", request, details);
  }

  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<ApiError> handleDatabase(DataAccessException ex, HttpServletRequest request) {
    log.error("Erreur base de données", ex);
    return buildResponse(ErrorCode.DATABASE_ERROR, "Erreur lors de l'accès à la base de données.", request, ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleUnexpected(Exception ex, HttpServletRequest request) {
    log.error("Erreur inattendue", ex);
    return buildResponse(ErrorCode.UNEXPECTED_ERROR, "Une erreur interne est survenue. Veuillez réessayer plus tard.", request, ex.getMessage());
  }
}
