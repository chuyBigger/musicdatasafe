package com.aluracursos.MusicDataSafe.model;

import java.time.LocalDate;

public class Album {

    private Long id;
    private String titulo;
    private String artista;
    private LocalDate fechaDeLanzamiento;
    private String genero;
    private Integer totalDeDuracion;
    private Integer totalDeCaciones;

    public Album(Long id, String titulo, String artista, LocalDate fechaDeLanzamiento, String genero, Integer totalDeDuracion, Integer totalDeCaciones) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
        this.genero = genero;
        this.totalDeDuracion = totalDeDuracion;
        this.totalDeCaciones = totalDeCaciones;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getTotalDeDuracion() {
        return totalDeDuracion;
    }

    public void setTotalDeDuracion(Integer totalDeDuracion) {
        this.totalDeDuracion = totalDeDuracion;
    }

    public Integer getTotalDeCaciones() {
        return totalDeCaciones;
    }

    public void setTotalDeCaciones(Integer totalDeCaciones) {
        this.totalDeCaciones = totalDeCaciones;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento +
                ", genero='" + genero + '\'' +
                ", totalDeDuracion=" + totalDeDuracion +
                ", totalDeCaciones=" + totalDeCaciones +
                '}';
    }
}

