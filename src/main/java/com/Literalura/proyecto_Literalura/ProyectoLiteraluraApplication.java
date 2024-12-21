package com.Literalura.proyecto_Literalura;

import com.Literalura.proyecto_Literalura.principal.Principal;
import com.Literalura.proyecto_Literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.LiteraLura.proyecto_LiteraLura")
public class ProyectoLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private Principal principal;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoLiteraluraApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		principal.muestraMenu();
	}
}
