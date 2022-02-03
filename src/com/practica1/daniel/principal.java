package com.practica1.daniel;

import java.util.Locale;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class principal {

    //Datos del jugador
    private static int idJugador = 1;
    private static String nombre_jugador;
    private static int vidas = 3;
    private static int punteo = 0;

    //Matriz para jugadores
    private static String matrizJugadores[][] = new String[50][3];


    //Items del juego
    final private static String FANTASMA = " @ ";
    final private static String PREMIO_SIMPLE = " 0 ";
    final private static String PREMIO_ESPECIAL = " $ ";
    final private static String PARED = " X ";
    final private static String PACMAN = " < ";

    //Teclas de movimiento
    final private static String ARRIBA = "8";
    final private static String ABAJO = "5";
    final private static String DERECHA = "6";
    final private static String IZQUIERDA = "4";
    final private static String PAUSA = "f";

    //Variables para Scanner
    private static int opciones_menu;
    private static int opciones_premios;
    private static int opciones_paredes;
    private static int opciones_trampas;

    //Tablero y consola
    private static String opciones_tablero;
    private static String espaciado = "======";

    //Matriz
    private static int nFilas, nColumnas;
    private static String matrizTableroP[][];

    //Variables de la clase Scanner
    private static Random ran = new Random();
    private static Scanner accionesJuego = new Scanner(System.in);
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

    //Metodo para el menu principal
    public static void menuPrincipal(){

        boolean val1 = true;
        while(val1){
            System.out.println(espaciado + "MENÚ DE INICIO"+espaciado +
                    "\n1. Iniciar Juego\n" +
                    "2. Historial de partidas\n" +
                    "3. Salir\r");

            opciones_menu = opcionesMenu.nextInt();

            switch (opciones_menu){
                case 1 :
                    val1 = false;

                    System.out.println("Escriba tu nombre: \r");
                    nombre_jugador = nombreJugador.nextLine();

                    System.out.println("Bienvenido " + nombre_jugador + "\n");
                    matrizJugadores[idJugador][0] = nombre_jugador;
                    idJugador++;

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
                    historialPartidas();

                    break;

                case 3 : System.out.println("Hasta pronto");
                    break;

                default: System.out.println("Debe elegir un número entre 1 y 3 ");
            }
        }
    }

    public static void historialPartidas(){
        System.out.println(espaciado + "HISTORIAL DE PARTIDAS" + espaciado);
        matrizJugadores[0][0] = "No.     ";
        matrizJugadores[0][1] = "USUARIO       ";
        matrizJugadores[0][2] = "PUNTEO";


        if(matrizJugadores[1][0] != null){
            for (int i = 0 ; i < matrizJugadores.length; i++ ){
                for ( int j = 0 ; j < matrizJugadores[i].length; j++){
                    if(matrizJugadores[i][j] != null){
                        if(i == 0){
                            System.out.print(matrizJugadores[i][j]);
                        }else {
                            System.out.print(matrizJugadores[i][j] + "      ");
                        }
                        if( j == 2){
                            System.out.println();
                        }

                    }
                }
            }
        } else{
            System.out.println("Aun no hay ninguna partida\n");
            menuPrincipal();
        }

    }
    //Metodo para movimientos
    public static void movimientosPacman(){

        boolean val = true;
        while(val){
            String tecla;
            System.out.print("Accion : ");
            tecla = accionesJuego.nextLine();

            if( tecla == ARRIBA || tecla == ABAJO || tecla == DERECHA || tecla == IZQUIERDA || tecla.toLowerCase() == PAUSA){
                val = false;
                switch (tecla){
                    case ARRIBA:
                        break;
                    case ABAJO:
                        break;
                    case DERECHA:
                        break;
                    case IZQUIERDA:
                        break;
                }


            }
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
        movimientosPacman();

    }

    public static void dibujarTablero(){

        for (int i=0; i < nFilas ; i++){
            for (int j=0; j<nColumnas ; j++){
                System.out.print(matrizTableroP[i][j]);
            }
            System.out.println();
        }

    }

    public static void validacionMovimientos(){

    }
    //Clase principal
    public static void main(String ...args){

        menuPrincipal();
    }
}
