package br.com.alura.challenge.LiterAlura;

import br.com.alura.challenge.LiterAlura.controllers.PrincipalController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LiterAluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(PrincipalController principalController) {
		return args -> {
			principalController.showMenu();
		};
	}

}
