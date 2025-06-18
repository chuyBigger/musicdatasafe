package com.aluracursos.MusicDataSafe.model;

import java.time.LocalDate;
import java.util.List;

public record DatosAlbum(
        Long id,
        String titulo,
        List<Artista>artista,
        List<Cancion> canciones,
        LocalDate fechaDeLanzamiento,
        String genero,
        Integer totalDeDuracion,
        Integer totalDeCaciones

) {
}
