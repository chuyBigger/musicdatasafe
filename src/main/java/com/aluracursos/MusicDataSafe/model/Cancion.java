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
    @JoinColumn(name = "album_id")
    private Album album;

    private Double duracion;
    @ManyToMany
    @JoinTable(
            name = "cancion_artista",
            joinColumns = @JoinColumn(name = "cancion_id"),
            inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    private List<Artista> artistas;
    private LocalDate fechaDeLanzamiento;
    private String posicionEnElAlbum;

    public Cancion(){}

    public Cancion(Long id, String nombre, List<Artista> artistas,Album album, Double duracion, LocalDate fechaDeLanzamiento ,String posicionEnElAlbum) {
        this.id = id;
        this.nombre = nombre;
        this.artistas = artistas;
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

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Double getDuracion() {
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
                ", artistas=" + (artistas != null ? artistas.stream().map(Artista::getNombre).toList() : "null") +
                ", album='" + (album != null ? album.getTitulo() : "null") + '\'' +
                ", duracion=" + duracion +
                ", posicionEnElAlbum='" + posicionEnElAlbum + '\'' +
                '}';
    }

}
