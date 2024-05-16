package com.br.api.luizawishlist.wishlist.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	 @Bean
	    OpenAPI customOpenAPI() {
	        return new OpenAPI()
	            .components(new Components().addSecuritySchemes("bearerAuth",
	                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
	            .info(new Info()
	                .title("Luiza APIs")
	                .version("v1")
	                .description("Luiza World")
	                .termsOfService("https://github.com/PedroFog")
	                .license(new License().name("Apache 2.0").url("https://github.com/PedroFog")))
	            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
	    }

}
