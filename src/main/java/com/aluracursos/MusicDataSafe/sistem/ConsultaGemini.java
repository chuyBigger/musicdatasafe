package com.aluracursos.MusicDataSafe.sistem;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;



public class ConsultaGemini {

    String apiKey = System.getenv("API_KEY_GEMINI");

    public String consultaMusicaGemini(String tipoBusqueda, String objetoBuscado) {
        String prompt = "Devuélveme solo un objeto JSON con la información de una " + tipoBusqueda + ".\n" +
                objetoBuscado +
                "Los campos deben tener los siguientes tipos y estructuras:\n\n" +
                "- nombre: tipo String\n" +
                "- album: objeto con estos campos:\n" +
                "    - titulo: String\n" +
                "    - artistas: lista de objetos (ver estructura de 'artistas')\n" +
                "    - canciones: lista de objetos con solo el campo 'nombre' (String)\n" +
                "    - fechaDeLanzamiento: tipo LocalDate\n" +
                "    - genero: String\n" +
                "    - totalDeDuracion: Integer\n" +
                "    - totalDeCanciones: Integer\n" +
                "- duracion: tipo Double (en minutos)\n" +
                "- artistas: lista de objetos con estos campos:\n" +
                "    - nombre: String\n" +
                "    - miniImagen: String\n" +
                "    - totalDeAlbums: Integer\n" +
                "- fechaDeLanzamiento: tipo LocalDate\n" +
                "- posicionEnElAlbum: tipo Integer\n\n" +
                "Devuelve únicamente el JSON. No incluyas texto explicativo ni comentarios. Como si respondieras desde una API.";

        return consultaGemini(prompt);

    }
    public String consultaLibreGemini(String consulta){
        String prompt = consulta;
        return consultaGemini(prompt);
    }

    private String consultaGemini(String prompt){
        String modelo = "gemini-2.0-flash-lite";
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
