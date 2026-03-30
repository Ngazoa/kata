package com.alten.shop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  // === Generaux validation ===
  INVALID_PARAMS(901, HttpStatus.BAD_REQUEST, "Paramètres invalides"),
  CONFLICT_ERROR(902, HttpStatus.CONFLICT, "Conflit détecté"),

  // === Utilisateur ===
  USER_ALREADY_EXISTS(1001, HttpStatus.CONFLICT, "L'utilisateur existe déjà"),
  USER_NOT_FOUND(1002, HttpStatus.NOT_FOUND, "Utilisateur non trouvé"),
  INVALID_USER_ROLE(1003, HttpStatus.BAD_REQUEST, "Rôle utilisateur invalide"),
  UNAUTHORIZED_ACTION(1004, HttpStatus.FORBIDDEN, "Action non autorisée"),

  // === Sécurité & Auth ===
  INVALID_TOKEN(7001, HttpStatus.UNAUTHORIZED, "Token invalide"),
  ACCESS_DENIED(7002, HttpStatus.FORBIDDEN, "Accès refusé"),
  AUTHENTICATION_FAILED(7003, HttpStatus.UNAUTHORIZED, "Échec de l'authentification"),

  // === Validation générique ===
  VALIDATION_ERROR(8001, HttpStatus.BAD_REQUEST, "Erreur de validation"),
  GENERATION_RAPPORTS_ERROR(8001, HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Erreur de la generation des rapports"),

  // === Technique ===
  DATABASE_ERROR(9001, HttpStatus.INTERNAL_SERVER_ERROR, "Erreur de base de données"),
  EXTERNAL_SERVICE_ERROR(9002, HttpStatus.BAD_GATEWAY, "Erreur de service externe"),
  EXTERNAL_TAUX_CONVERSION_ERROR(9003, HttpStatus.BAD_GATEWAY, "Erreur de service de converssion devises"),
  UNEXPECTED_ERROR(9999, HttpStatus.INTERNAL_SERVER_ERROR, "Erreur inattendue");

  // === Champs ===
  private final int number;

  private final HttpStatus httpStatus;

  private final String description;

  // Constructeur
  ErrorCode(int number, HttpStatus httpStatus) {
    this(number, httpStatus, null);
  }

  // Constructeur complet
  ErrorCode(int number, HttpStatus httpStatus, String description) {
    this.number = number;
    this.httpStatus = httpStatus;
    this.description = description != null ? description : formatDefaultDescription();
  }

  // Génère la description par défaut à partir du nom de l'enum
  private String formatDefaultDescription() {
    String formatted = this.name().replace("_", " ").toLowerCase();
    return formatted.substring(0, 1).toUpperCase() + formatted.substring(1);
  }
}
