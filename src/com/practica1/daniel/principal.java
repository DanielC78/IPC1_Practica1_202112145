package com.practica1.daniel;

import java.util.Scanner;
import java.util.Random;

public class principal {

    //Datos del jugador
    private static int idJugador = 1;
    private static String nombre_jugador;
    private static int vidas = 3;
    private static int punteo = 0;

    //Matriz para jugadores
    private static String matrizJugadores[][] = new String[50][3];
    private static int Pacman_x, Pacman_y;


    //Items del juego
    final private static String FANTASMA = " @ ";
    final private static String PREMIO_SIMPLE = " 0 ";
    final private static String PREMIO_ESPECIAL = " $ ";
    final private static String PARED = " X ";
    final private static String PACMAN_IZQ = " < ";
    final private static String PACMAN_DER = " > ";
    final private static String PACMAN_ARRIBA = " ^ ";
    final private static String PACMAN_ABAJO = " v ";
    final private static String BLANCO = "   ";

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

    //Variables para el juego
    private static String mensajeAlerta = "¡NO PUEDES PASAR!";
    private static int cantidadPremios;

    //Tablero y consola
    private static String opciones_tablero;
    private static String espaciado = "======";
    final private static String BORDE_SUPERIOR = "_";
    final private static String BORDE_LATERAL = "|";

    //Matriz
    private static int nFilas, nColumnas;
    private static String matrizTableroP[][];

    //Objetos de la clase Scanner
    private static Random ran = new Random();
    private static Scanner posX_Pacman = new Scanner(System.in);
    private static Scanner posY_Pacman = new Scanner(System.in);
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
                    nombre_jugador = nombreJugador.nextLine().toUpperCase();

                    System.out.println("Bienvenido " + nombre_jugador+ "\n");
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
                    val1 = false;
                    break;

                default: System.out.println("Debe elegir un número entre 1 y 3 ");
            }
        }
    }

    public static void menuPausa(){

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
        boolean val1 = true;

        //Preguntar el punto de inicio
        while(val1){
            System.out.println("Ingrese la posición de inicio");
            System.out.print("Fila: ");
            Pacman_x = posX_Pacman.nextInt();
            System.out.print("Columna: ");
            Pacman_y = posY_Pacman.nextInt();

            if(matrizTableroP[Pacman_x][Pacman_y] == BLANCO){
                val1 = false;
                matrizTableroP[Pacman_x][Pacman_y] = PACMAN_IZQ;
                dibujarTablero();

            } else{
                System.out.println("La casilla ya esta llena");
                dibujarTablero();
            }

        }

        String tecla;
        //Mover entre el tablero
        while((cantidadPremios > 0) && (vidas > 0)){
            //System.out.println(cantidadPremios);
            //System.out.println(vidas);


            System.out.print("Accion : ");
            tecla = accionesJuego.nextLine();

                switch (tecla.toLowerCase()){
                    case ARRIBA:
                        if(matrizTableroP[Pacman_x - 1][Pacman_y] != PARED){
                            setPunteoVidas(tecla);
                            matrizTableroP[Pacman_x][Pacman_y] = BLANCO;
                            if(Pacman_x - 1 == 0){
                                Pacman_x = nFilas-1;
                            }
                            Pacman_x --;
                            matrizTableroP[Pacman_x][Pacman_y] = PACMAN_ARRIBA;
                        } else{
                            System.out.println(mensajeAlerta);
                        }
                        break;
                    case ABAJO:
                        if(matrizTableroP[Pacman_x + 1][Pacman_y] != PARED){
                            setPunteoVidas(tecla);
                            matrizTableroP[Pacman_x][Pacman_y] = BLANCO;
                            if(Pacman_x + 1 == nFilas-1 ){
                                Pacman_x = 0;
                            }
                            Pacman_x ++;
                            matrizTableroP[Pacman_x][Pacman_y] = PACMAN_ABAJO;
                        }else{
                            System.out.println(mensajeAlerta);
                        }

                        break;
                    case DERECHA:
                        if(matrizTableroP[Pacman_x][Pacman_y + 1] == PARED){
                            setPunteoVidas(tecla);
                            matrizTableroP[Pacman_x][Pacman_y] = BLANCO;
                            if(Pacman_y + 1 == nColumnas - 1){
                                Pacman_y = 0;
                            }

                            Pacman_y++;
                            matrizTableroP[Pacman_x][Pacman_y] = PACMAN_DER;
                        } else{
                            System.out.println(mensajeAlerta);
                        }

                        break;

                    case IZQUIERDA:
                        if(matrizTableroP[Pacman_x][Pacman_y - 1] == PARED){
                            setPunteoVidas(tecla);
                            matrizTableroP[Pacman_x][Pacman_y] = BLANCO;
                            if(Pacman_y - 1 == 0){
                                Pacman_y = nColumnas-1;
                            }
                            Pacman_y--;
                            matrizTableroP[Pacman_x][Pacman_y] = PACMAN_IZQ;
                        } else{
                            System.out.println(mensajeAlerta);
                        }

                        break;

                    case PAUSA:
                        menuPausa();
                        break;
                    default: System.out.println("Debe de ingresar una tecla válida");
                }
                dibujarTablero();
        }

        idJugador++;
    }

    public static void setPunteoVidas(String _tecla){
        switch (_tecla){
            case ARRIBA:
                if(matrizTableroP[Pacman_x - 1][Pacman_y] == PREMIO_ESPECIAL){
                    punteo += 15;
                } else if(matrizTableroP[Pacman_x - 1][Pacman_y] == PREMIO_SIMPLE){
                    punteo += 10;
                } else if(matrizTableroP[Pacman_x - 1][Pacman_y] == FANTASMA){
                    vidas--;
                }
                break;
            case ABAJO:
                if(matrizTableroP[Pacman_x + 1][Pacman_y] == PREMIO_ESPECIAL){
                    punteo += 15;
                } else if(matrizTableroP[Pacman_x + 1][Pacman_y] == PREMIO_SIMPLE){
                    punteo += 10;
                } else if(matrizTableroP[Pacman_x + 1][Pacman_y] == FANTASMA){
                    vidas--;
                }
                break;
            case DERECHA:
                if(matrizTableroP[Pacman_x][Pacman_y + 1] == PREMIO_ESPECIAL){
                    punteo += 15;
                } else if(matrizTableroP[Pacman_x][Pacman_y + 1] == PREMIO_SIMPLE){
                    punteo += 10;
                } else if( matrizTableroP[Pacman_x][Pacman_y + 1] == FANTASMA){
                    vidas--;
                }
                break;
            case IZQUIERDA:
                if(matrizTableroP[Pacman_x][Pacman_y - 1] == PREMIO_ESPECIAL){
                    punteo += 15;
                } else if(matrizTableroP[Pacman_x][Pacman_y - 1] == PREMIO_SIMPLE){
                    punteo += 10;
                } else if(matrizTableroP[Pacman_x][Pacman_y - 1] == FANTASMA){
                    vidas--;
                }
                break;
        }
        matrizJugadores[idJugador][2] = String.valueOf(punteo);
    }

    public static int calcularPremios(){
       cantidadPremios = (int)Math.round((nFilas * nColumnas)*(opciones_premios/100.0));
       return cantidadPremios;
    }

    //Metodo para dibujar el tablero
    public static void llenarTablero(){
        matrizTableroP = new String[nFilas][nColumnas];

        //Crear el margen
        for(int i = 0 ; i < nFilas; i++){
            matrizTableroP[i][0]= BORDE_LATERAL;
            matrizTableroP[i][nColumnas-1]=BORDE_LATERAL;
        }

        for(int j = 0 ; j < nColumnas; j++){
            if(j == 0 || j == nColumnas-1){
                matrizTableroP[0][j]= BORDE_SUPERIOR;
                matrizTableroP[nFilas-1][j]= BORDE_SUPERIOR;
            } else{
                matrizTableroP[0][j]= " "+BORDE_SUPERIOR+" ";
                matrizTableroP[nFilas-1][j]=" "+BORDE_SUPERIOR+" ";
            }
        }

        //Rellenar con Premios

        int llenadoPremios  = calcularPremios();

        while(llenadoPremios > 0){
            int numeroRan_x = (int)(ran.nextDouble()*nFilas);
            int numeroRan_y = (int)(ran.nextDouble()*nColumnas);
            int numeroRan_P = (int) (ran.nextDouble()*2);

            for (int i = 0; i < nFilas; i++){
                for(int j = 0; j < nColumnas; j++){

                    if( matrizTableroP[numeroRan_x][numeroRan_y] == null && matrizTableroP[numeroRan_x][numeroRan_y] != "|" && matrizTableroP[numeroRan_x][numeroRan_y] != "_"){
                        switch (numeroRan_P){
                            case 0:
                                matrizTableroP[numeroRan_x][numeroRan_y] = PREMIO_SIMPLE;
                                llenadoPremios--;
                                break;
                            case 1:
                                matrizTableroP[numeroRan_x][numeroRan_y] = PREMIO_ESPECIAL;
                                llenadoPremios--;
                                break;
                        }

                    } else {continue;}
                }
            }
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
                        porcentajeParedes--;
                    }
                }
            }
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
                        porcentajeFantasmas--;
                    }
                }
            }
        }

        //Dejar espacios en blanco
        for(int i = 0; i < nFilas ; i++){
            for (int j = 0; j < nColumnas; j++){
                if(matrizTableroP[i][j] == null){
                    matrizTableroP[i][j] = BLANCO;
                }
            }
        }
        dibujarTablero();
        movimientosPacman();
    }

    public static void dibujarTablero(){

        for (int i=0; i < nFilas ; i++){
            for (int j=0; j<nColumnas ; j++){
                System.out.print(matrizTableroP[i][j]);
            }
            System.out.println();
        }
        estadisticasJugador();

    }

    //Clase principal
    public static void main(String ...args){

        menuPrincipal();
    }
}
