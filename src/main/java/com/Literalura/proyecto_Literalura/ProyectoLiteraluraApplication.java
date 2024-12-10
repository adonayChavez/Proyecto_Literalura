package com.Literalura.proyecto_Literalura;

import com.Literalura.proyecto_Literalura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoLiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraMenu();
	}
}
