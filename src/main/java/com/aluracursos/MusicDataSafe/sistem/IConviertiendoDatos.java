package com.aluracursos.MusicDataSafe.sistem;

public interface IConviertiendoDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
