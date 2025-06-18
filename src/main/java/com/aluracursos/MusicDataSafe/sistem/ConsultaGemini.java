package com.aluracursos.MusicDataSafe.sistem;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;


public class ConsultaGemini {

    public String consultaMusicaGemini(String data, String detalleBusqueda) {
        String modelo = "gemini-2.0-flash-lite";
        String prompt = "Devuélveme un JSON con la información de la canción solicitada. " +
                "Incluye los campos: " + data +      // aqui hay que agregar los datos a buscar en el json dependiendo de la busqueda
                detalleBusqueda +                    // aqui va el prompot de busqueda
                "Solo responde con el JSON, sin texto adicional.";

        String apiKey = System.getenv("API_KEY_GEMINI");
        Client cliente = new Client.Builder().apiKey(apiKey).build();
        try {
            GenerateContentResponse respuesta = (cliente).models.generateContent(
                    modelo,
                    prompt,
                    null
            );
            if (!respuesta.text().isEmpty()) {
                return respuesta.text();
            }
        } catch (Exception e) {
            System.out.println("Error al llamar a la API de Gemini: " + e.getMessage());
        }
        return null;

    }

}
