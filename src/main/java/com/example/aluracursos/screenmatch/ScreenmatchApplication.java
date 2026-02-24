package com.example.aluracursos.screenmatch;

import com.example.aluracursos.screenmatch.module.DatosEpisodio;
import com.example.aluracursos.screenmatch.module.DatosSerie;
import com.example.aluracursos.screenmatch.module.DatosTemporadas;
import com.example.aluracursos.screenmatch.principal.Principal;
import com.example.aluracursos.screenmatch.service.ConsumoAPI;
import com.example.aluracursos.screenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

    @java.lang.Override
    public void run(java.lang.String... args) throws Exception {

        Principal principal = new Principal();1
        principal.muestraElMenu();

    }
}

