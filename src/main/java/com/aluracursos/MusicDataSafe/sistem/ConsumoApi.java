package com.aluracursos.MusicDataSafe.sistem;

import ch.qos.logback.core.joran.conditional.ThenAction;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    private String url;

    public String obtenerDatos(String url) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statuscode = response.statusCode();

            if (statuscode >= 200 && statuscode < 300) {
                var json = response.body();
                return json;
            } else {
                throw new RuntimeException("Error en la respuesta del servidor: (" + statuscode + ") " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error al consumir el Api" + e.getMessage());
        }
    }

}
