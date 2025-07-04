package com.aluracursos.MusicDataSafe.repositorio;

import com.aluracursos.MusicDataSafe.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long>{

    Optional<Album> findByTituloIgnoreCase(String AlbumBuscado);

}
