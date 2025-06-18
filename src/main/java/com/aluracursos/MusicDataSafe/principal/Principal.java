package com.aluracursos.MusicDataSafe.principal;


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
    List<Cancion> cancionsBuscadas = new ArrayList<>();

    public void muestraElmenu() {

        int opcionInt = -1;

        while (opcionInt != 0) {
            var menu = """
                        =====================================================
                                Bienvenido al sistema Music Data Safe
                            Muestra informcion de musica albunes y artstas
                        ªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªª
                    
                                  Elije una opcion para continuar:
                    
                                    1.- Buscar una Cancion
                                    2.- Buscar Una Album
                                    3.- Buscar Un Artsita Muscial
                                    4.- Ver Canciones buscadas
                                    0.- Salir
                    
                        =====================================================
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
                    //todo buscarUnAlbum();
                    break;
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
        }

        String jsonGemini = consultaGemini.consultaMusicaGemini(data, prompt);
        String json = jsonGemini.replace("```json", "").replace("```", "").replace("`", "").trim();
        Cancion jsonCancion = conviertiendoDatos.obtenerDatos(json, Cancion.class);
        cancionsBuscadas.add(jsonCancion);
        System.out.println(jsonCancion);
    }

    public void mostrarListaDeBusquedas(){
        System.out.println("Esta es la lista de registros: 📃");
        cancionsBuscadas.forEach(System.out::println);
    }
}






