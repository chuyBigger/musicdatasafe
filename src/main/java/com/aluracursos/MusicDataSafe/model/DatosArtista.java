package com.aluracursos.MusicDataSafe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosArtista(
        @JsonAlias("id")Long id,
        @JsonAlias("name")String nombre,
        @JsonAlias("picture")String miniImagen,
        @JsonAlias("nb_album")Integer totalDeAlbums
) {
}
