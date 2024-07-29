package com.onboarding.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("EMPLOYEE ONBOARDING REST-API")
                        .version("1.0")
                        .description("API documentation for EMPLOYEE ONBOARDING application")
                        .contact(new Contact()
                        		.name("Somanath Das")
                                .email("dassomanath@gmail.com")
                                .url("https://www.employeeonboarding.com"))
                        .license(new License()
                        		.name("Apache 2.0")
                                .url("https://www.employeeonboarding.com/license")));
	}
}
