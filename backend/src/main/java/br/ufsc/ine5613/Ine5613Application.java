package br.ufsc.ine5613;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class Ine5613Application {

	public static void main(String[] args) {
		SpringApplication.run(Ine5613Application.class, args);
	}

	@Bean
	public OpenAPI apiDetails() {
		return new OpenAPI()
				.info(new Info()
						.title("INE5613 Rest API")
						.description("API Rest para o trabalho final da disciplina de Bancos de Dados I")
						.version("1.0")
						.license(new License()
								.name("GNU GENERAL PUBLIC LICENSE")
								.url("https://www.gnu.org/licenses/gpl-3.0-standalone.html")
						)
						.contact(new Contact()
								.name("Pedro Binotto")
								.email("pedrosantibinotto@gmail.com")
								.url("http://github.com/PedroBinotto")
						)
				);
	}

}
