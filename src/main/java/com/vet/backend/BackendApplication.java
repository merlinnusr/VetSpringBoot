package com.vet.backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Adopcion de mascotas REST API",
		version = "1.0",
		description = "Adopcion de mascotas REST API Spring Boot 3 REST API"
))
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
