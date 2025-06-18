package com.aluracursos.MusicDataSafe.sistem;

import com.aluracursos.MusicDataSafe.model.Album;
import com.aluracursos.MusicDataSafe.model.TipoBusqueda;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CrearUrl {
    public static final String URL_BASE = "https://api.deezer.com/search?q=";
    public String urlOk;
    public String anexoOk;

    public String urlCompuesta(TipoBusqueda tipo, String url) {
        String tipoBusqueda = switch (tipo) {
            case ALBUM -> "album:";
            case ARTISTA -> "artist:";
            case CANCION -> "track:";
        };
        var urlCodificada = URLEncoder.encode(url, StandardCharsets.UTF_8);
        urlOk = URL_BASE + tipoBusqueda + '"' + urlCodificada + '"';
        return urlOk;
    }

    // crea el anexo de url en caso de hacer busqueda con 2 parametros artista y cancion
    public String UrlArtista(String artistaBuscado, String cancionBuscada) {
        String query = "artist:\"" + artistaBuscado + "\" track:\"" + cancionBuscada + "\"";
        String queryCodificada = URLEncoder.encode(query, StandardCharsets.UTF_8);
        return URL_BASE+queryCodificada;
    }
}
