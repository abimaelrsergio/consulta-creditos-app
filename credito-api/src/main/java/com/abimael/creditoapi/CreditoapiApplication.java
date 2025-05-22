package com.abimael.creditoapi;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Documentação do microserviço Consulta Credito microservice REST API",
				description = "API RESTful para a consulta de créditos constituídos. A API fornece informações essenciais como número do crédito constituído, número da NFS-e, data da constituição do crédito, valor do ISSQN, tipo do crédito e outros atributos.",
				version = "v1",
				contact = @Contact(
						name = "Abimael Sergio",
						email = "abimaelr.sergio@gmail.com",
						url = "https://www.linkedin.com/in/abimaelsergio/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		)
)
@SpringBootApplication
public class CreditoapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditoapiApplication.class, args);
	}

}
