package com.aluracursos.MusicDataSafe.model;

public class Cancion {

    private Long id;
    private String nombre;
    private Album album;
    private Integer duracion;
    private String vistaPrevia;
    private Artista contribuciones;

    public Cancion(Long id, String nombre, Artista artista, Album album, Integer duracion, String vistaPrevia) {
        this.id = id;
        this.nombre = nombre;
        this.contribuciones = artista;
        this.album = album;
        this.duracion = duracion;
        this.vistaPrevia = vistaPrevia;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Artista getArtista() {
        return contribuciones;
    }

    public void setArtista(Artista artista) {
        this.contribuciones = artista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getVistaPrevia() {
        return vistaPrevia;
    }

    public void setVistaPrevia(String vistaPrevia) {
        this.vistaPrevia = vistaPrevia;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", artista='" + contribuciones + '\'' +
                ", album='" + album + '\'' +
                ", duracion=" + duracion +
                ", vistaPrevia='" + vistaPrevia + '\'' +
                '}';
    }
}
