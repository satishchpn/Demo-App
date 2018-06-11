package com.zycus.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/* It is for Swagger UI */
	/*@Bean
	public Docket employeeApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("sample-spring-swagger-api").apiInfo(apiInfo())
				.select().paths(regex("/api/swaggerEndPoint.*")).build();
	}*/

	/* It is for Swagger Api Info */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring REST Sample with Swagger")
				.description("Spring REST Sample with Swagger").license("Apache License Version 2.0").version("1.0")
				.build();
	}
	
	@Bean
	public Docket docket() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.protocols(new HashSet<String>(Arrays.asList("http")));
		docket.apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.zycus.endpoint"))
				.paths(PathSelectors.any()).build();
		return docket;
	}

}
