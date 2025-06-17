package com.aluracursos.MusicDataSafe.sistem;

import ch.qos.logback.core.joran.conditional.ThenAction;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    public String obtenerDatos(String url) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(""))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int staruscode = response.statusCode();

            if (staruscode >= 200 && staruscode < 300) {
                return response.body();
            } else {
                throw new RuntimeException("Error en la respuesta del servidor: (" + staruscode + ") " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error al consumir el Api" + e.getMessage());
        }
    }

}
