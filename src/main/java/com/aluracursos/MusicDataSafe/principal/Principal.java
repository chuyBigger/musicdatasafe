package com.aluracursos.MusicDataSafe.principal;


import com.aluracursos.MusicDataSafe.model.Album;
import com.aluracursos.MusicDataSafe.model.Artista;
import com.aluracursos.MusicDataSafe.model.Cancion;
import com.aluracursos.MusicDataSafe.repositorio.AlbumRepository;
import com.aluracursos.MusicDataSafe.repositorio.ArtistaRepository;
import com.aluracursos.MusicDataSafe.repositorio.CancionRepositorio;
import com.aluracursos.MusicDataSafe.sistem.ConsultaGemini;
import com.aluracursos.MusicDataSafe.sistem.ConvierteDatos;
import com.aluracursos.MusicDataSafe.sistem.CrearUrl;
import com.aluracursos.MusicDataSafe.sistem.IConviertiendoDatos;
import org.checkerframework.checker.units.qual.C;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final CancionRepositorio repositorioCancion;
    private final AlbumRepository repositorioAlbum;
    private final ArtistaRepository repositorioArtista;
    CrearUrl crearUrl = new CrearUrl();
    ConsultaGemini consultaGemini = new ConsultaGemini();

    IConviertiendoDatos conviertiendoDatos = new ConvierteDatos();
    Scanner scanner = new Scanner(System.in);
    List<Cancion> cancionesBuscadas = new ArrayList<>();
    List<Artista> artistasBuscados = new ArrayList<>();
    List<Album> albumsBuscados = new ArrayList<>();


    public Principal(CancionRepositorio repositoryCancion, AlbumRepository repositoryAlbum, ArtistaRepository repositoryArtista) {
        this.repositorioCancion = repositoryCancion;
        this.repositorioAlbum = repositoryAlbum;
        this.repositorioArtista = repositoryArtista;
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
                                        5.- Realizar una Cosulta en Geminiº
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
                case 3:
                    // todo buscarUnArtista();
                    break;
                case 4:
                    mostrarListaDeBusquedas();
                    break;
                case 5:
                    generarConsultaGemini();
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

        if (buscarArtista.isEmpty()) {
            System.out.println("⚠️ Podría verse afectado el resultado de la busqueda sin artista.");
        }

        Optional<Cancion> cancionDb = repositorioCancion.findByNombreIgnoreCase(buscarCancion);

        if (cancionDb.isPresent()) {
            System.out.println("🎶 Cancion en con trada en la base de datos!!");
            System.out.println(cancionDb.get());
        } else {
            System.out.println(" ⚠️ Cancion no encontrada en la DB, 🔎🔎 Consultando API... ");
        }
        // Realizando la busqueda en Ai Gemini

        String tipoBusqueda = "de una canción musical.";    // Ajusta el prompt para el Tipo de busqueda cancion
        String prompt = buscarArtista.isEmpty()
                ? "La canción es '" + buscarCancion + "'. "
                : "La canción es '" + buscarCancion + "' y el artista es '" + buscarArtista + "'. ";
        String jsonGemini = consultaGemini.consultaMusicaGemini(tipoBusqueda, prompt);
        System.out.println(jsonGemini);

        // Limpia el json que tiene detalles por no ser json puro al venir de una Ai
        String json = jsonGemini.replace("```json", "").replace("```", "").replace("`", "").trim();
        Cancion jsonCancion = conviertiendoDatos.obtenerDatos(json, Cancion.class);
        System.out.println(jsonCancion);

        // buscar si existe un artista

        List<Artista> artistasPersistidos = new ArrayList<>();

        for (Artista artista : jsonCancion.getArtistas()) {
            Optional<Artista> artistaExistente = repositorioArtista.findByNombreIgnoreCase(artista.getNombre());

            if (artistaExistente.isPresent()) {
                System.out.println("🎤 El artista '" + artista.getNombre() + "' ya existe en la base de datos.");
                artistasPersistidos.add(artistaExistente.get());
            } else {
                Artista nuevo = repositorioArtista.save(artista);
                System.out.println("🆕 Artista guardado: " + nuevo.getNombre());
                artistasPersistidos.add(nuevo);
            }
        }

        Album album = jsonCancion.getAlbum();
        album.setArtistas(artistasPersistidos);

        // consultas antes de guardar album y artista

        Optional<Album> albumExiste = repositorioAlbum.findByTituloIgnoreCase(album.getTitulo());
        Album albumFinal;
        if (albumExiste.isPresent()) {
            albumFinal = albumExiste.get();
            System.out.println("📀 Álbum ya existe: " + albumFinal.getTitulo());
        } else {
            albumFinal = repositorioAlbum.save(album);
            System.out.println("💾 Álbum guardado: " + albumFinal.getTitulo());
        }

        jsonCancion.setAlbum(albumFinal);

        System.out.println("🎶 Cancion Encontrada: " + jsonCancion);
    }

    public void mostrarListaDeBusquedas() {

    }

    public void generarConsultaGemini() {
        int opcion = 1;
        System.out.println("Hola bienvenido a consultale a gemini\n");
        do {
            System.out.println("Que deseas consulta a gemini Cualquier cosa");
            var consulta = scanner.nextLine();
            String respuesta = consultaGemini.consultaLibreGemini(consulta);
            System.out.println(respuesta);

            boolean opcionValida = false;
            while (!opcionValida){

                System.out.println("\nSi deseas hacer otra consulta presion 1 si deseas salir preciona 0 ");
                String entrada = scanner.nextLine();

                try {
                    opcion = Integer.parseInt(entrada);
                    if (opcion == 0 || opcion == 1) {
                        opcionValida = true;
                    }else {
                        System.out.println("⚠️ Opcion no valida");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("⛔ Entrada Invalida" + e.getMessage());
                }
            }
        } while (opcion > 0);
        System.out.println("Hasta luego 👋🏻👋🏻👋🏻");

    }
}
