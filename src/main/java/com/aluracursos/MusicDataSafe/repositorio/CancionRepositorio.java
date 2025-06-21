package com.aluracursos.MusicDataSafe.repositorio;

import com.aluracursos.MusicDataSafe.model.Cancion;
import org.checkerframework.checker.units.qual.C;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CancionRepositorio extends JpaRepository<Cancion, Long> {

    Optional<Cancion> findByNombreContainsIgnoreCase(String cancionBuscar);

    Optional<Cancion> findByNombreIgnoreCase(String cancionBuscar);


}
