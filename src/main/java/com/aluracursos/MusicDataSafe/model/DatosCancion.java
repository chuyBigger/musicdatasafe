package com.aluracursos.MusicDataSafe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.util.List;

public record DatosCancion(
        @JsonAlias Long id,
        @JsonAlias("title") String nombre,
        @JsonAlias("title")String album,
        @JsonAlias("duration")Integer duracion,
        @JsonAlias("release_date") LocalDate fechaDeLanzamiento,
        @JsonAlias("track_position")String posisicionEnElAlmbun,
        List<DatosArtista> contributors
){
}
