package br.ufsc.ine5613;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class Ine5613Application {

	public static void main(String[] args) {
		SpringApplication.run(Ine5613Application.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.ufsc.ine5613"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(
				"INE5613 Rest API",
				"API Rest para o trabalho final da disciplina de Bancos de Dados I",
				"1.0",
				"https://desciclopedia.org/wiki/Porra_nenhuma",
				new Contact("Pedro Binotto", "http://github.com/PedroBinotto", "pedrosantibinotto@gmail.com"),
				"GNU GENERAL PUBLIC LICENSE",
				"https://www.gnu.org/licenses/gpl-3.0-standalone.html",
				Collections.emptyList()
		);
	}

}
