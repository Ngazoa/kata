package com.alten.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class ShopApplication {

	private static final Logger log = LoggerFactory.getLogger(ShopApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	@PostConstruct
	public void logUrls() {
		log.info("=== URLs disponibles au démarrage ===");
		log.info("GET  /api/users            Récupérer tous les utilisateurs");
		log.info("GET  /api/users/{{username}}  Récupérer un utilisateur par username");
		log.info("POST /api/users           Ajouter un utilisateur");

		log.info("GET  /api/products         Récupérer tous les produits");
		log.info("GET  /api/products/{{name}}  Récupérer un produit par nom");
		log.info("POST /api/products        Ajouter un produit");

		log.info("Swagger UI: http://localhost:8080/swagger-ui.html");
		log.info("OpenAPI JSON: http://localhost:8080/v3/api-docs");
		log.info("====================================");
	}
}