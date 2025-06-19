package com.aluracursos.MusicDataSafe.principal;


import com.aluracursos.MusicDataSafe.model.Album;
import com.aluracursos.MusicDataSafe.model.Artista;
import com.aluracursos.MusicDataSafe.model.Cancion;
import com.aluracursos.MusicDataSafe.repositorio.CancionRepositorio;
import com.aluracursos.MusicDataSafe.sistem.ConsultaGemini;
import com.aluracursos.MusicDataSafe.sistem.ConvierteDatos;
import com.aluracursos.MusicDataSafe.sistem.CrearUrl;
import com.aluracursos.MusicDataSafe.sistem.IConviertiendoDatos;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final CancionRepositorio repositorio;
    CrearUrl crearUrl = new CrearUrl();
    ConsultaGemini consultaGemini = new ConsultaGemini();
    IConviertiendoDatos conviertiendoDatos  = new ConvierteDatos();
    Scanner scanner = new Scanner(System.in);
    List<Cancion> cancionesBuscadas = new ArrayList<>();
    List<Artista> artistasBuscados = new ArrayList<>();
    List<Album> albumsBuscados = new ArrayList<>();

    public Principal(CancionRepositorio repository) {
        this.repositorio = repository;
    }

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
                    // todo buscarUnAlbum();
                    break;
                case 3:{
                    // todo buscarUnArtista();
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

        System.out.println("Ingresa el nombre de la cancion que buscas: ");
        String buscarCancion = scanner.nextLine();

        System.out.println("""
                !! Para una mejor busqueda es mejor agregar el artista
                si deseas agregar el nombre del artista agregalo ahora
                """);
        String buscarArtista = scanner.nextLine();

        if (buscarArtista.isEmpty()){
            System.out.println("⚠️ Podría verse afectado el resultado de la busqueda sin artista.");
        }

        Optional<Cancion> cancionDb = repositorio.findByNombreContainsIgnoreCase(buscarCancion);

        if (cancionDb.isPresent()){
            System.out.println("🎶 Cancion en con trada en la base de datos!!");
            System.out.println(cancionDb.get());
        }else {
            System.out.println(" ⚠️ Cancion no encontrada en la DB, 🔎🔎 Consultando API... ");
        }
        // Realizando la busqueda en Ai Gemini

        String tipoBusqueda = "de una canción musical.";    // Ajusta el prompt para el Tipo de busqueda cancion
        String prompt = buscarArtista.isEmpty()
                ? "La canción es '" + buscarCancion +  "'. "
                : "La canción es '" + buscarCancion + "' y el artista es '" + buscarArtista + "'. ";

        String jsonGemini = consultaGemini.consultaMusicaGemini(tipoBusqueda, prompt);
        System.out.println(jsonGemini);
        // Limpia el json que tiene detalles por no ser json puro al venir de una Ai
        String json = jsonGemini.replace("```json", "").replace("```", "").replace("`", "").trim();
        System.out.println(json);
        Cancion jsonCancion = conviertiendoDatos.obtenerDatos(json, Cancion.class);
        cancionesBuscadas.add(jsonCancion);
        repositorio.save(jsonCancion);
        System.out.println("🎶 Cancion Encontrada: "+ jsonCancion);

    }





    public void mostrarListaDeBusquedas(){
        System.out.println("Esta es la lista de registros: 📃");
        cancionesBuscadas.forEach(System.out::println);
    }
}
