package com.aluracursos.MusicDataSafe.principal;

import com.aluracursos.MusicDataSafe.model.Cancion;

import java.util.Scanner;

public class Principal {

    public void muestraElmenu(){
        Scanner scanner = new Scanner(System.in);
        int opcionInt = -1;

        while (opcionInt != 0){
            var menu = """
                    =====================================================
                            Bienvenido al sistema Music Data Safe
                        Muestra informcion de musica albunes y artstas
                    ªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªª
                    
                              Elije una opcion para continuar:
                              
                                1.- Buscar una Cancion
                                2.- Buscar Una Album
                                3.- Buscar Un Artsita Muscial
                                0.- Salir
                                           
                    =====================================================
                """;
            System.out.println(menu);
            var opcion = scanner.nextLine();
            try {
                opcionInt = Integer.parseInt(opcion);
            }catch (NumberFormatException e ){
                System.out.println("Error: debes ingresar un número entero.");
                continue;
            }
            switch (opcionInt){
                case 1:
                    buscarUnaCancion();
                    break;
                case 2:
                    //todo buscarUnAlbum();
                    break;
                case 0:
                    System.out.println("Gracias por usar nuestro sistema");
                    System.out.println("Hasta Luego ...");
                    opcionInt = 0;
                    break;
                default:
                    System.out.println("Haz ingresado un valor o opcion invalida vuelve a intentarlo");
                    break;
            }

        }
    }

    private void buscarUnaCancion(){
        System.out.println("Ingresa el nombre de la cancion: ");
    }
}
