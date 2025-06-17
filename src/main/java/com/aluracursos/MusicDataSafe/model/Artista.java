package com.aluracursos.MusicDataSafe.model;

public class Artista {

    private Long id;
    private String nombre;
    private String miniImagen;
    private Integer totalDeAlbums;

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
