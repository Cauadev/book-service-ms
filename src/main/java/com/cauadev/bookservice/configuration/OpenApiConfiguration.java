package com.cauadev.bookservice.configuration;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition(info = 
@Info(title = "Book Service API",
	  version = "v1",
	  description = "Documentation of Book Microservice",
	  contact = @Contact(
			  	email = "cauadev@gmail.com",
			  	name = "Cauã Nunes",
			  	url = "https://github.com/cauadev"
			  ))
)
public class OpenApiConfiguration {
	
	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
					.components(new Components())
					.info(new io.swagger.v3.oas.models.info.Info()
						  .title("Book Microservice")
						  .version("v1")
						  .license(new License()
								  .name("Apache 2.0")
								  .url("https://springdoc.org")
								  )
						  );
	}

}
