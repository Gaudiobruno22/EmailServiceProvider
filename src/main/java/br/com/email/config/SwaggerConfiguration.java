package br.com.email.config;

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

//Access to UI - http://localhost:8080/swagger-ui.html
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.email"))
					.paths(PathSelectors.any())
					.build()
					.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {			
		return new ApiInfo(
				"API Rest EmailServiceProvider - Spring 2.7.12",
				"Documentação opara Visualização dos EndPoints do Gerenciador de E-mail.",
				"v1",
				"Termos de Serviço: Não tem.",
				new Contact("Bruno Gaudio", "https://github.com/Gaudiobruno22", "brunogmattos22@gmail.com"),
				"Licença Gratuita", 
				"https://www.linkedin.com/in/bruno-gaudio/",
				Collections.emptyList());
	}
}
