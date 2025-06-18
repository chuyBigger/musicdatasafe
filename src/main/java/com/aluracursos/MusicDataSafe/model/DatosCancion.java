package com.aluracursos.MusicDataSafe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.util.List;

public record DatosCancion(
        @JsonAlias Long id,
        @JsonAlias("nombre") String nombre,
        @JsonAlias("album")String album,
        @JsonAlias("duracion")Double duracion,
        @JsonAlias("artista")List<String> artista,
        @JsonAlias("fechaDeLanzamiento") LocalDate fechaDeLanzamiento,
        @JsonAlias("posisicionEnElAlbum")String posisicionEnElAlbum
){
}
