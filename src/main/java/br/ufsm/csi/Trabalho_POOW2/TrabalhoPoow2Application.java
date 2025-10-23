package br.ufsm.csi.Trabalho_POOW2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Trabalho Poow2",
				version = "1.0",
				description = "Projeto para agendamento de servicos",
				contact = @Contact(name = "Suporte", email = "suporte@exemplo")
		)
)
@SpringBootApplication
public class TrabalhoPoow2Application {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoPoow2Application.class, args);
	}

}
