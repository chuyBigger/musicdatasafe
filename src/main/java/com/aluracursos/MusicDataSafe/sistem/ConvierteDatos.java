package com.aluracursos.MusicDataSafe.sistem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ConvierteDatos implements IConviertiendoDatos {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public <T> T obtenerDatos(String json, Class<T> clase) {
        try{
            return objectMapper.readValue(json, clase);
        }catch (Exception e){
            throw new RuntimeException("Error al convertir Datos", e);
        }
    }
}
