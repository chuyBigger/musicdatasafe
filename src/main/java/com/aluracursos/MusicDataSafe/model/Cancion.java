package com.aluracursos.MusicDataSafe.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "canciones")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToOne
    private String album;
    private Double duracion;
    @ManyToMany
    private List<String> artista;
    private LocalDate fechaDeLanzamiento;
    private String posicionEnElAlbum;

    public Cancion(){}

    public Cancion(Long id, String nombre, List<String> artista, String album, Double duracion, LocalDate fechaDeLanzamiento ,String posicionEnElAlbum) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.album = album;
        this.duracion = duracion;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
        this.posicionEnElAlbum = posicionEnElAlbum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getArtista() {
        return artista;
    }

    public void setArtista(List<String> artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public String getPosicionEnElAlbum() {
        return posicionEnElAlbum;
    }

    public void setPosicionEnElAlbum(String posicionEnElAlbum) {
        this.posicionEnElAlbum = posicionEnElAlbum;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", artista=" + artista +
                ", album='" + album + '\'' +
                ", duracion=" + duracion +
                ", posicionEnElAlbum" + posicionEnElAlbum + '\'' +
                '}';
    }
}
