package com.aluracursos.MusicDataSafe.repositorio;

import com.aluracursos.MusicDataSafe.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Optional<Artista> findByNombreIgnoreCase(String artistaBuscado);

}
