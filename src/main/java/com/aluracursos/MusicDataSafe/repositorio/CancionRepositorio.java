package com.aluracursos.MusicDataSafe.repositorio;

import com.aluracursos.MusicDataSafe.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CancionRepositorio extends JpaRepository<Cancion, Long> {

    Optional<Cancion> findByNombreContainsIgnoreCase(String cancionBuscar);

}
