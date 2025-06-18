package com.aluracursos.MusicDataSafe.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String miniImagen;
    private Integer totalDeAlbums;

    @ManyToMany(mappedBy = "artistas")
    private List<Cancion> canciones= new ArrayList<>();

    @ManyToMany(mappedBy = "artistas")
    private List<Album> albums= new ArrayList<>();

    public Artista(){}

    public Artista(Long id, String nombre, String miniImagen, Integer totalDeAlbums) {
        this.id = id;
        this.nombre = nombre;
        this.miniImagen = miniImagen;
        this.totalDeAlbums = totalDeAlbums;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMiniImagen() {
        return miniImagen;
    }

    public void setMiniImagen(String miniImagen) {
        this.miniImagen = miniImagen;
    }

    public Integer getTotalDeAlbums() {
        return totalDeAlbums;
    }

    public void setTotalDeAlbums(Integer totalDeAlbums) {
        this.totalDeAlbums = totalDeAlbums;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", miniImagen='" + miniImagen + '\'' +
                ", totalDeAlbums=" + totalDeAlbums +
                '}';
    }

}
