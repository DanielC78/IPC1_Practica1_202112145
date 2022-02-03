package com.practica1.daniel;

import java.util.Scanner;
import java.util.Random;

public class principal {

    //Datos del jugador
    private static String nombre_jugador;
    private static int vidas = 3;
    private static int punteo = 0;

    //Items del juego
    private static String FANTASMA = " @ ";
    private static String PREMIO_SIMPLE = " 0 ";
    private static String PREMIO_ESPECIAL = " $ ";
    private static String PARED = " X ";
    private static String PACMAN = " < ";

    //Variables para Scanner
    private static int opciones_menu;
    private static int opciones_premios;
    private static int opciones_paredes;
    private static int opciones_trampas;


    private static String opciones_tablero;

    //Matriz
    private static int nFilas, nColumnas;
    private static String matrizTableroP[][];

    //Variables de la clase Scanner
    private static Random ran = new Random();
    private static Scanner nombreJugador = new Scanner(System.in);
    private static Scanner opcionesMenu = new Scanner(System.in);
    private static Scanner opcionTablero = new Scanner(System.in);
    private static Scanner opcionPremios = new Scanner(System.in);
    private static Scanner opcionesParedes = new Scanner(System.in);
    private static Scanner opcionesTrampas = new Scanner(System.in);

    //Metodo para estadisticas del jugador
    public static void estadisticasJugador(){
        System.out.println("Usuario: " + nombre_jugador);
        System.out.println("Punteo: " + punteo);
        System.out.println("Vidas: " + vidas);

    }
    //Metodo para movimientos
    public static void movimientosPacman(String tecla){
        switch (tecla.toString().toUpperCase()){
            case "8":
                break;
            case "5":
                break;
            case "6":
                break;
            case "4":
                break;
            default: System.out.println("Debe ingresar una tecla válida");
        }

    }

    //Metodo para dibujar el tablero
    public static void llenarTablero(){
        matrizTableroP = new String[nFilas][nColumnas];

        //Crear el margen
        for(int i = 0 ; i < nFilas; i++){
            matrizTableroP[i][0]="|";
            matrizTableroP[i][nColumnas-1]="|";
        }

        for(int j = 0 ; j < nColumnas; j++){
           if(nColumnas == 12){
               matrizTableroP[0][j]="__";
               matrizTableroP[nFilas-1][j]="__";
           } else{
               matrizTableroP[0][j]="--";
               matrizTableroP[nFilas-1][j]="--";
           }
        }

        //Rellenar con Premios

        int porcentajePremios = (int)Math.round((nFilas * nColumnas)*(opciones_premios/100.0));

        while(porcentajePremios > 0){
            int numeroRan_x = (int)(ran.nextDouble()*nFilas);
            int numeroRan_y = (int)(ran.nextDouble()*nColumnas);
            int numeroRan_P = (int) (ran.nextDouble()*1);


            for (int i = 0; i < nFilas; i++){
                for(int j = 0; j < nColumnas; j++){

                    if( matrizTableroP[numeroRan_x][numeroRan_y] == null && matrizTableroP[numeroRan_x][numeroRan_y] != "|" && matrizTableroP[numeroRan_x][numeroRan_y] != "_"){

                        switch (numeroRan_P){
                            case 0:
                                matrizTableroP[numeroRan_x][numeroRan_y] = PREMIO_SIMPLE;
                                break;
                            case 1:
                                matrizTableroP[numeroRan_x][numeroRan_y] = PREMIO_ESPECIAL;
                                break;
                        }

                    } else {continue;}
                }
            }

            porcentajePremios--;
        }

        //Llenar con Paredes
        int porcentajeParedes = (int)Math.round((nFilas * nColumnas)*(opciones_paredes/100.0));

        while(porcentajeParedes > 0){
            int numeroRan_x = (int)(ran.nextDouble()*nFilas);
            int numeroRan_y = (int)(ran.nextDouble()*nColumnas);

            for (int i = 0; i < nFilas; i++){
                for(int j = 0; j < nColumnas; j++){

                    if( matrizTableroP[numeroRan_x][numeroRan_y] == null && matrizTableroP[numeroRan_x][numeroRan_y] != "|" && matrizTableroP[numeroRan_x][numeroRan_y] != "_" && matrizTableroP[numeroRan_x][numeroRan_y] != PREMIO_ESPECIAL && matrizTableroP[numeroRan_x][numeroRan_y] != PREMIO_SIMPLE){

                        matrizTableroP[numeroRan_x][numeroRan_y] = PARED;
                    }
                }
            }

            porcentajeParedes--;
        }


        //Llenar con fantasmas
        int porcentajeFantasmas = (int)Math.round((nFilas * nColumnas)*(opciones_trampas/100.0));

        while(porcentajeFantasmas > 0){
            int numeroRan_x = (int)(ran.nextDouble()*nFilas);
            int numeroRan_y = (int)(ran.nextDouble()*nColumnas);

            for (int i = 0; i < nFilas; i++){
                for(int j = 0; j < nColumnas; j++){

                    if( matrizTableroP[numeroRan_x][numeroRan_y] == null && matrizTableroP[numeroRan_x][numeroRan_y] != "|" && matrizTableroP[numeroRan_x][numeroRan_y] != "_" && matrizTableroP[numeroRan_x][numeroRan_y] != PREMIO_ESPECIAL && matrizTableroP[numeroRan_x][numeroRan_y] != PREMIO_SIMPLE && matrizTableroP[numeroRan_x][numeroRan_y] != PARED){

                        matrizTableroP[numeroRan_x][numeroRan_y] = FANTASMA;
                    }
                }
            }

            porcentajeFantasmas--;
        }

        //Dejar espacios en blanco
        for(int i = 0; i < nFilas ; i++){
            for (int j = 0; j < nColumnas; j++){
                if(matrizTableroP[i][j] == null){
                    matrizTableroP[i][j] = "   ";
                }
            }
        }

        dibujarTablero();
        estadisticasJugador();

    }

    public static void dibujarTablero(){

        for (int i=0; i < nFilas ; i++){
            for (int j=0; j<nColumnas ; j++){
                System.out.print(matrizTableroP[i][j]);
            }
            System.out.println();
        }

    }

    public static void main(String ...args){

        String espaciado = "=====";

        boolean val1 = true;

        while(val1){
            System.out.println(espaciado + "MENÚ DE INICIO"+espaciado +
                    "\n1. Iniciar Juego\n" +
                    "2. Historial de partidas\n" +
                    "3. Salir");

            opciones_menu = opcionesMenu.nextInt();

            switch (opciones_menu){
                case 1 :
                    val1 = false;

                    System.out.println("Escriba tu nombre: \r");
                    nombre_jugador = nombreJugador.nextLine();

                    System.out.println("Bienvenido " + nombre_jugador + "\n");


                    System.out.print(espaciado +" ESPECIFICAR TABLERO"+espaciado+
                            "\nPOR FAVOR INGRESE LOS SIGUIENTES VALORES\n\r");

                    System.out.print("TABLERO:  ");
                    opciones_tablero = opcionTablero.nextLine();

                    System.out.print("PREMIOS [1-40]:   ");
                    opciones_premios = opcionPremios.nextInt();

                    System.out.print("PAREDES [1-20]:   ");
                    opciones_paredes = opcionesParedes.nextInt();

                    System.out.print("TRAMPAS [1-20]:   ");
                    opciones_trampas = opcionesTrampas.nextInt();

                    //Opciones para el tablero
                    switch (opciones_tablero.toUpperCase()){
                        case "P":
                            nFilas = 7;
                            nColumnas = 8;
                            llenarTablero();
                            break;

                        case "G":
                            nFilas = 12;
                            nColumnas = 12;
                            llenarTablero();
                            break;
                    }
                    break;
                case 2 :
                    val1 = false;
                    System.out.println("Op2");
                    break;

                case 3 : System.out.println("Hasta pronto");
                    break;

                default: System.out.println("Debe elegir un número entre 1 y 3 ");
            }
        }
    }
}
