package com.practica1.daniel;


import java.util.Locale;
import java.util.Scanner;
import java.util.Random;

public class principal {
    public static void main(String ...args){
        int opciones;
        String tablero;
        boolean val1 = true;
        Scanner entrada = new Scanner(System.in);
        Scanner _tablero = new Scanner(System.in);

        //Menú de inicio de usuario
        System.out.println("====MENÚ DE INICIO====\n" +
                "1.  Iniciar juego\n" +
                "2.  Historial de partidas\n" +
                "3.  Salir\n");

        //Opciones de juego
        while(val1){
            boolean val2 = true;
            opciones = entrada.nextInt();

            if(opciones>3){
                System.out.println("Debe ingresar un número entre 1 y 3");

                //Inicio de juego
            } else if(opciones == 1){
                val1 = false;

                //Elección del Tablero
                while(val2){
                    System.out.println("====ESPECIFICAR TABLERO====\n " +
                            "POR FAVOR, INGRESE LOS SIGUIENTES VALORES\n" +
                            "TABLERO: \r");
                    tablero = _tablero.nextLine();

                    if(tablero.equalsIgnoreCase("G")){
                        val2 = false;

                    } else if(tablero.equalsIgnoreCase("P")){
                        val2 = false;

                    } else{
                        System.out.println("Debe ingresar 'G' o 'P'\n");
                    }
                }

            } else if(opciones == 2){
                val1 = false;

            } else if(opciones == 3){
                val1 = false;
            }
        }



    }
}
