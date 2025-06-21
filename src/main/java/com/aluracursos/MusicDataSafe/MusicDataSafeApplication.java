package com.aluracursos.MusicDataSafe;

import com.aluracursos.MusicDataSafe.principal.Principal;
import com.aluracursos.MusicDataSafe.repositorio.AlbumRepository;
import com.aluracursos.MusicDataSafe.repositorio.ArtistaRepository;
import com.aluracursos.MusicDataSafe.repositorio.CancionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicDataSafeApplication implements CommandLineRunner {

	@Autowired
	private CancionRepositorio repositoryCancion;
	@Autowired
	private AlbumRepository repositoryAlbum;
	@Autowired
	private ArtistaRepository repositoryArtista;

	public static void main(String[] args) {
		SpringApplication.run(MusicDataSafeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositoryCancion, repositoryAlbum, repositoryArtista);
		principal.muestraElmenu();

	}



}

