package com.leonlyon.products.tools;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.leonlyon.products.controllers"))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
			"Product Service API",
			"Product Service API Description",
			"1.0",
			"http://demosite.com/terms",
			new Contact("Álex León", "http://demosite.com", "alex.leon@demosite.com"),
			"LICENSE",
			"LICENSE URL",
			Collections.emptyList()
		);
	}

}
