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

    @ManyToMany
    @JoinTable(
            name = "album_artista",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    private List<Artista> artistas;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Cancion> canciones;
    private LocalDate fechaDeLanzamiento;
    private String genero;
    private Integer totalDeDuracion;
    private Integer totalDeCanciones;

    public Album(){}

    public Album(Long id, String titulo, List<Artista> artistas, List<Cancion> canciones, LocalDate fechaDeLanzamiento, String genero, Integer totalDeDuracion, Integer totalDeCanciones) {
        this.id = id;
        this.titulo = titulo;
        this.artistas = artistas;
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

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
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
                ", artistas=" + (artistas != null ? artistas.stream().map(Artista::getNombre).toList() : "[]") +
                ", canciones=" + (canciones != null ? canciones.stream().map(Cancion::getNombre).toList() : "[]") +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento +
                ", genero='" + genero + '\'' +
                ", totalDeDuracion=" + totalDeDuracion +
                ", totalDeCanciones=" + totalDeCanciones +
                '}';
    }

}

