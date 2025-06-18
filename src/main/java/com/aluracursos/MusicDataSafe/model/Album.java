package com.aluracursos.MusicDataSafe.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    @ManyToOne
    private List<String> artista;
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<String> canciones;
    private LocalDate fechaDeLanzamiento;
    private String genero;
    private Integer totalDeDuracion;
    private Integer totalDeCanciones;

    public Album(){}

    public Album(Long id, String titulo, List<String> artista, List<String> canciones, LocalDate fechaDeLanzamiento, String genero, Integer totalDeDuracion, Integer totalDeCanciones) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.canciones = canciones;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
        this.genero = genero;
        this.totalDeDuracion = totalDeDuracion;
        this.totalDeCanciones = totalDeCanciones;
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

    public List<String> getArtista() {
        return artista;
    }

    public void setArtista(List<String> artista) {
        this.artista = artista;
    }

    public List<String> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<String> canciones) {
        this.canciones = canciones;
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

    public Integer getTotalDeCanciones() {
        return totalDeCanciones;
    }

    public void setTotalDeCanciones(Integer totalDeCanciones) {
        this.totalDeCanciones = totalDeCanciones;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", canciones='" + canciones +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento +
                ", genero='" + genero + '\'' +
                ", totalDeDuracion=" + totalDeDuracion +
                ", totalDeCanciones=" + totalDeCanciones +
                '}';
    }
}

