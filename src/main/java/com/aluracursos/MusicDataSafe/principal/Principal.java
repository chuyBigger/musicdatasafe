package com.aluracursos.MusicDataSafe.principal;


import com.aluracursos.MusicDataSafe.model.Album;
import com.aluracursos.MusicDataSafe.model.Artista;
import com.aluracursos.MusicDataSafe.model.Cancion;
import com.aluracursos.MusicDataSafe.sistem.ConsultaGemini;
import com.aluracursos.MusicDataSafe.sistem.ConvierteDatos;
import com.aluracursos.MusicDataSafe.sistem.CrearUrl;
import com.aluracursos.MusicDataSafe.sistem.IConviertiendoDatos;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    CrearUrl crearUrl = new CrearUrl();
    ConsultaGemini consultaGemini = new ConsultaGemini();
    IConviertiendoDatos conviertiendoDatos  = new ConvierteDatos();
    Scanner scanner = new Scanner(System.in);
    List<Cancion> cancionesBuscadas = new ArrayList<>();
    List<Artista> artistasBuscados = new ArrayList<>();
    List<Album> albumsBuscados = new ArrayList<>();

    public void muestraElmenu() {

        int opcionInt = -1;

        while (opcionInt != 0) {
            var menu = """
                             ======0<0=================================================
                                     Bienvenido al sistema Music Data Safe
                                  Muestra información de música, álbumes y artistas
                             =========================================================
                    
                                        Elige una opción para continuar:
                    
                                        1.- Buscar una Canción
                                        2.- Buscar un Álbum
                                        3.- Buscar un Artista Musical
                                        4.- Ver Canciones Buscadas
                                        0.- Salir
                    
                             ==========================================================
                    """;
            System.out.println(menu);
            var opcion = scanner.nextLine();
            try {
                opcionInt = Integer.parseInt(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Error: debes ingresar un número entero.");
                continue;
            }
            switch (opcionInt) {
                case 1:
                    buscarUnaCancion();
                    break;
                case 2:
                    buscarUnAlbum();
                    break;
                case 3:{
                    buscarUnArtista();
                    break;
                }
                case 4:
                    mostrarListaDeBusquedas();
                    break;
                case 0:
                    System.out.println("Gracias por usar nuestro sistema");
                    System.out.println("Hasta Luego ...");
                    opcionInt = 0;
                    break;
                default:
                    System.out.println("Haz ingresado un valor o opcion invalida vuelve a intentarlo");
            }
        }
    }

    private void buscarUnaCancion() {
        String solicitud = "de la canción solicitada.";
        String data = "nombre(String), album(String), duracion(en minutos)(Double), artista(si hay más de un artista, sepáralos por coma)(Array), fechaDeLanzamiento(LocalDate),posicionEnElAlbum(Integer)";

        System.out.println("Ingresa el nombre de la cancion que buscas: ");
        String buscarCancion = scanner.nextLine();

        System.out.println("""
                !! Para una mejor busqueda es mejor agregar el artista
                si deseas agregar el nombre del artista agregalo ahora
                """);
        String buscarArtista = scanner.nextLine();

        String prompt = buscarArtista.isEmpty()
                ? "La canción es '" + buscarCancion +  "'. "
                : "La canción es '" + buscarCancion + "' y el artista es '" + buscarArtista + "'. ";

        if (buscarArtista.isEmpty()){
            System.out.println("⚠️ Podría verse afectado el resultado sin artista.");
        } else {
            System.out.println("🎶 Cancion encontrada !!");
        }

        String jsonGemini = consultaGemini.consultaMusicaGemini(data, prompt, solicitud);
        String json = jsonGemini.replace("```json", "").replace("```", "").replace("`", "").trim();
        Cancion jsonCancion = conviertiendoDatos.obtenerDatos(json, Cancion.class);
        cancionesBuscadas.add(jsonCancion);
        System.out.println(jsonCancion);
    }

    public void buscarUnAlbum(){
        String solicitud = "del album musical solicitado que coincida por nombre de la solicitud.";
        String data = "String titulo, artista(si hay más de un artista, sepáralos por coma)(Array), cancion(si hay más de un cancion sepáralos por coma y solo pon el titulo de la cancion)(Array), LocalDate fechaDeLanzamiento, String genero, Double totalDeDuracion, Integer totalDeCaciones;";

        System.out.println("Ingresa el nombre del album musical que buscas: ");
        String buscarAlbum = scanner.nextLine();

        if (buscarAlbum.isEmpty()){
            System.out.println("⚠️ No ha ingresado ningun valor intente de nuevo");
            return;
        }else {
            System.out.println("🙌🏻 Album encontrado !!");
        }
        String prompt = "El album es '" + buscarAlbum +  "'. ";
        String jsonGemini = consultaGemini.consultaMusicaGemini(data, prompt, solicitud);
        String json = jsonGemini.replace("```json", "").replace("```", "").replace("`", "").trim();
        System.out.println(json);
        Album jsonAlbum = conviertiendoDatos.obtenerDatos(json, Album.class );
        albumsBuscados.add(jsonAlbum);
        System.out.println(jsonAlbum);
    }

    public void buscarUnArtista(){

        String solicitud = "del artista musical solicitado que coincida por nombre de la solicitud.";
        String data = "String nombre, String miniImagen, Integer totalDeAlbums,";

        System.out.println("Ingresa el nombre del o de la Cantante que buscas: ");
        String buscarArtista = scanner.nextLine();

        if (buscarArtista.isEmpty()){
            System.out.println("⚠️ No ha ingresado ningun valor intente de nuevo");
            return;
        }else {
            System.out.println("🙌🏻 Artista encontrado !!");
        }
        String prompt = "El artista es '" + buscarArtista +  "'. ";
        String jsonGemini = consultaGemini.consultaMusicaGemini(data, prompt, solicitud);
        String json = jsonGemini.replace("```json", "").replace("```", "").replace("`", "").trim();
        System.out.println(json);
        Artista jsonArtista = conviertiendoDatos.obtenerDatos(json, Artista.class );
        artistasBuscados.add(jsonArtista);
        System.out.println(jsonArtista);
    }

    public void mostrarListaDeBusquedas(){
        System.out.println("Esta es la lista de registros: 📃");
        cancionesBuscadas.forEach(System.out::println);
    }
}






