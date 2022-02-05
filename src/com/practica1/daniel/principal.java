package com.practica1.daniel;

import sun.lwawt.macosx.CSystemTray;

import java.util.Scanner;
import java.util.Random;

public class principal {

    //Datos del jugador
    private static String nombre_jugador;
    private static int vidas = 3;
    private static int punteo = 0;

    //Matriz para jugadores
    private static String matrizJugadores[][] = new String[50][3];
    private static int Pacman_x = 0, Pacman_y = 0;


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

    //Variables para Scanner tipo entero
    private static int opciones_menu;
    private static int opciones_premios;
    private static int opciones_paredes;
    private static int opciones_trampas;

    //Variables para Scanner tipo String
    private static String S_menu ;
    private static String S_premio;
    private static String S_paredes;
    private static String S_trampas;

    //Tablero y consola
    private static String opciones_tablero;
    final private static String PEQ = "P";
    final private static String GRAN = "G";
    private static String espaciado = "======";
    final private static String BORDE_SUPERIOR = "_";
    final private static String BORDE_LATERAL = "|";
    private static String mensajeAlerta = "¡NO PUEDES PASAR!";
    private static int cantidadPremios;

    //Matriz
    private static int nFilas, nColumnas;
    private static String matrizTableroP[][];

    /*Objetos de la clase Scanner*/

    //Obtener la posición del PACMAN
    private static Random ran = new Random();
    private static Scanner posX_Pacman = new Scanner(System.in);
    private static Scanner posY_Pacman = new Scanner(System.in);
    private static Scanner accionesJuego = new Scanner(System.in);

    //Opciones del menu
    private static Scanner getOpcionesPausa = new Scanner(System.in);
    private static int opciones_pausa;

    //Opciones para el tablero
    private static Scanner nombreJugador = new Scanner(System.in);
    private static Scanner opcionesMenu = new Scanner(System.in);
    private static Scanner opcionTablero = new Scanner(System.in);
    private static Scanner opcionPremios = new Scanner(System.in);
    private static Scanner opcionesParedes = new Scanner(System.in);
    private static Scanner opcionesTrampas = new Scanner(System.in);

    public static void estadisticasJugador(){
        System.out.println("Usuario: " + nombre_jugador);
        System.out.println("Punteo: " + punteo);
        System.out.println("Vidas: " + vidas);
        System.out.println("Premios: " + cantidadPremios);
    }
    private static void tableroOpciones(String _tecla){
        switch (_tecla.toUpperCase()){
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
    }
    public static void menuPrincipal(){

        boolean VALIDACION = true;
        while(VALIDACION){
            System.out.println(espaciado + "MENÚ DE INICIO"+espaciado +
                    "\n1. Iniciar Juego\n" +
                    "2. Historial de partidas\n" +
                    "3. Salir\r");

            S_menu = opcionesMenu.nextLine();
            if(S_menu.matches("[1-9]")){
                opciones_menu = Integer.valueOf(S_menu);
                switch (opciones_menu){
                    case 1 :
                        VALIDACION = false;
                       while(true){
                           System.out.print("Escriba tu nombre: ");
                           nombre_jugador = nombreJugador.nextLine().toUpperCase();

                           if(nombre_jugador.matches("[A-Za-z]")){
                               System.out.print("Escriba tu nombre: ");
                               nombre_jugador = nombreJugador.nextLine().toUpperCase();
                               System.out.println("Bienvenido " + nombre_jugador+ "\n");
                               break;
                           }
                       }

                        System.out.print(espaciado +" ESPECIFICAR TABLERO"+espaciado+
                                "\nPOR FAVOR INGRESE LOS SIGUIENTES VALORES\n\r");

                        while(true){
                            System.out.print("TABLERO:  ");
                            opciones_tablero = opcionTablero.nextLine();


                            if(opciones_tablero.equalsIgnoreCase(PEQ) || opciones_tablero.equalsIgnoreCase(GRAN) ){
                                break;
                            }
                        }

                        while(true){
                            System.out.print("PREMIOS [1-40]:   ");
                            S_premio = opcionPremios.nextLine();
                            if(S_premio.matches("[1-9]")){
                                opciones_premios = Integer.valueOf(S_premio);
                                if(opciones_premios > 0 && opciones_premios <= 40){ break;}
                            }
                        }

                        while (true){
                            System.out.print("PAREDES [1-20]:   ");
                            S_paredes = opcionesParedes.nextLine();
                            if(S_paredes.matches("[1-9]")){
                                opciones_paredes = Integer.valueOf(S_paredes);
                                if(opciones_paredes > 0 && opciones_paredes <= 20){break;}
                            }
                        }

                        while (true){
                            System.out.print("TRAMPAS [1-20]:   ");
                            S_trampas = opcionesTrampas.nextLine();

                            if(S_trampas.matches("[1-9]")){
                                opciones_trampas = Integer.valueOf(S_trampas);
                                if(opciones_trampas > 0 && opciones_trampas <= 20){break;}
                            }
                        }
                        tableroOpciones(opciones_tablero);
                        break;

                    case 2 :
                        VALIDACION = false;
                        historialPartidas();
                        break;

                    case 3 : System.out.println("Hasta pronto");
                        VALIDACION = false;
                        System.exit(0);
                        break;

                    default: System.out.println("Debe elegir un número entre 1 y 3 ");
                }
            }
        }
    }
    public static void menuPausa(){
        System.out.println(espaciado + " PAUSA " + espaciado +"\n" +
                "POR FAVOR, SELECCIONE UNA OPCION\n" +
                "1. REGRESAR\n" +
                "2. TERMINAR PARTIDA\r");

        opciones_pausa = getOpcionesPausa.nextInt();

        while(true){
            if(opciones_pausa > 0 &&  opciones_pausa <=2){
                break;
            }
        }

        switch (opciones_pausa){
            case 1:
                dibujarTablero();
                movimientosPacman();
                break;
            case 2:
                System.out.println("¿Está seguro de terminar la partida? (S) para SI o (N) para NO");
                while(true){
                    Scanner RES = new Scanner(System.in);
                    System.out.print("Opcion: ");
                    String r = RES.nextLine();

                    if(r.equalsIgnoreCase("S")){
                        desplazarJugadores();
                        vaciarDatos();
                        menuPrincipal();
                        break;
                    } else if (r.equalsIgnoreCase("N")){
                        menuPausa();
                    }
                }
                break;
        }


    }
    public static void desplazarJugadores(){
        for(int i = 48; i >= 0; i--){
            for (int j = 1 ; j < matrizJugadores[i].length; j++){
                    if(matrizJugadores[i][j] != null){
                        matrizJugadores[i+1][j] = matrizJugadores[i][j];
                    }
            }
        }
        matrizJugadores[0][1] = nombre_jugador;
        matrizJugadores[0][2] = String.valueOf(punteo);


    }
    public static void vaciarDatos(){
        punteo = 0;
        vidas = 3;
        cantidadPremios = 0;
        Pacman_y = 0;
        Pacman_x = 0;
    }
    public static void historialPartidas(){
        System.out.println(espaciado + "HISTORIAL DE PARTIDAS" + espaciado +"\n" +
                "No.     " + "USUARIO       " + " PUNTEO");

        if(matrizJugadores[0][1] == null){
            System.out.println("Aun no hay ninguna partida\n");
            menuPrincipal();
        } else {
            for (int i = 0 ; i < matrizJugadores.length; i++ ){
                for ( int j = 0 ; j < matrizJugadores[i].length; j++){
                    if(matrizJugadores[i][1] != null){
                        matrizJugadores[i][0] = String.valueOf(i+1);
                        System.out.print(matrizJugadores[i][j] + "      ");
                        if( j == 2){
                            System.out.println();
                        }

                    }
                }
            }
        }
        System.out.println("Enter para regresar al menú");
        Scanner entrada = new Scanner(System.in);
        System.out.println();
        String en = entrada.nextLine();
        menuPrincipal();
    }
    public static void movimientosPacman(){

        boolean VALIDACION_MOV = true;

        //Preguntar el punto de inicio
        if(Pacman_x == 0 && Pacman_y == 0){
            while(VALIDACION_MOV){
                System.out.println("Ingrese la posición de inicio");
                while(true){
                    System.out.print("Fila: ");
                    Pacman_x = posX_Pacman.nextInt();
                    if(Pacman_x <= nFilas - 2 && Pacman_x > 0){
                        break;
                    } else{ System.out.println("Debe ser un numero entre 1 y " + (nFilas - 2));}
                }

                while (true){
                    System.out.print("Columna: ");
                    Pacman_y = posY_Pacman.nextInt();
                    if(Pacman_y <= nColumnas -2 && Pacman_y > 0){
                        break;
                    } else { System.out.println("Debe ser un numero entre 1 y " + (nColumnas - 2));}
                }

                if(matrizTableroP[Pacman_x][Pacman_y] == BLANCO){
                    matrizTableroP[Pacman_x][Pacman_y] = PACMAN_IZQ;
                    dibujarTablero();
                    VALIDACION_MOV = false;

                } else{
                    System.out.println("La casilla ya esta llena");
                    dibujarTablero();
                }
            }
        }

        String tecla;

        //Mover entre el tablero
        while((cantidadPremios > 0) && (vidas > 0)){
            System.out.print("Accion : ");
            tecla = accionesJuego.nextLine();
                switch (tecla.toLowerCase()){
                    case ARRIBA:
                        if(matrizTableroP[Pacman_x - 1][Pacman_y] == PARED){
                            System.out.println(mensajeAlerta);
                        } else{
                            setPunteoVidas(tecla);
                            matrizTableroP[Pacman_x][Pacman_y] = BLANCO;
                            if(Pacman_x - 1 == 0 && matrizTableroP[nFilas - 2][Pacman_y] != PARED){
                                Pacman_x = nFilas-1;
                            } else if(Pacman_x - 1 == 0 && matrizTableroP[nFilas - 2][Pacman_y] == PARED){
                                Pacman_x++;
                                System.out.println(mensajeAlerta);
                            }
                            Pacman_x --;
                            matrizTableroP[Pacman_x][Pacman_y] = PACMAN_ARRIBA;
                        }
                        break;

                    case ABAJO:
                        if(matrizTableroP[Pacman_x + 1][Pacman_y] == PARED){
                            System.out.println(mensajeAlerta);
                        }else{
                            setPunteoVidas(tecla);
                            matrizTableroP[Pacman_x][Pacman_y] = BLANCO;
                            if(Pacman_x + 1 == nFilas-1 && matrizTableroP[1][Pacman_y] != PARED){
                                Pacman_x = 0;
                            } else if(Pacman_x + 1 == nFilas-1 && matrizTableroP[1][Pacman_y] == PARED){
                                Pacman_x --;
                                System.out.println(mensajeAlerta);
                            }
                            Pacman_x ++;
                            matrizTableroP[Pacman_x][Pacman_y] = PACMAN_ABAJO;
                        }

                        break;

                    case DERECHA:
                        if(matrizTableroP[Pacman_x][Pacman_y + 1] == PARED){
                            System.out.println(mensajeAlerta);
                        } else{
                            setPunteoVidas(tecla);
                            matrizTableroP[Pacman_x][Pacman_y] = BLANCO;
                            if(Pacman_y + 1 == nColumnas - 1 && matrizTableroP[Pacman_x][1] != PARED ){
                                Pacman_y = 0;
                            } else if(Pacman_y + 1 == nColumnas - 1 && matrizTableroP[Pacman_x][1] == PARED){
                                Pacman_y--;
                                System.out.println(mensajeAlerta);
                            }
                            Pacman_y++;
                            matrizTableroP[Pacman_x][Pacman_y] = PACMAN_DER;
                        }

                        break;

                    case IZQUIERDA:
                        if(matrizTableroP[Pacman_x][Pacman_y - 1] == PARED){
                            System.out.println(mensajeAlerta);
                        } else{
                            setPunteoVidas(tecla);
                            matrizTableroP[Pacman_x][Pacman_y] = BLANCO;
                            if((Pacman_y - 1 == 0) && (matrizTableroP[Pacman_x][nColumnas - 2] != PARED)){
                                Pacman_y = nColumnas-1;
                            } else if((Pacman_y - 1 == 0) && matrizTableroP[Pacman_x][nColumnas - 2] == PARED){
                                Pacman_y++;
                                System.out.println(mensajeAlerta);
                            }
                            Pacman_y--;
                            matrizTableroP[Pacman_x][Pacman_y] = PACMAN_IZQ;
                        }
                        break;

                    case PAUSA:
                        menuPausa();
                        break;
                    default: System.out.println("Debe de ingresar una tecla válida");
                }
                dibujarTablero();
        }

        if(cantidadPremios == 0){
            System.out.println("¡HAS GANADO!");
        } else if(vidas == 0){
            System.out.println("Más suerte a la próxima :( \n");
        }

        desplazarJugadores();
        vaciarDatos();
        menuPrincipal();

    }
    public static void setPunteoVidas(String _tecla){
        switch (_tecla){
            case ARRIBA:
                if(matrizTableroP[Pacman_x - 1][Pacman_y] == PREMIO_ESPECIAL || matrizTableroP[nFilas - 2][Pacman_y] == PREMIO_ESPECIAL){
                    punteo += 15;
                    cantidadPremios--;
                } else if(matrizTableroP[Pacman_x - 1][Pacman_y] == PREMIO_SIMPLE || matrizTableroP[nFilas - 2][Pacman_y] == PREMIO_SIMPLE){
                    punteo += 10;
                    cantidadPremios--;
                } else if(matrizTableroP[Pacman_x - 1][Pacman_y] == FANTASMA){
                    vidas--;
                }
                break;
            case ABAJO:
                if(matrizTableroP[Pacman_x + 1][Pacman_y] == PREMIO_ESPECIAL || matrizTableroP[1][Pacman_y] == PREMIO_ESPECIAL){
                    punteo += 15;
                    cantidadPremios--;
                } else if(matrizTableroP[Pacman_x + 1][Pacman_y] == PREMIO_SIMPLE || matrizTableroP[1][Pacman_y] == PREMIO_ESPECIAL){
                    punteo += 10;
                    cantidadPremios--;
                } else if(matrizTableroP[Pacman_x + 1][Pacman_y] == FANTASMA){
                    vidas--;
                }
                break;
            case DERECHA:
                if(matrizTableroP[Pacman_x][Pacman_y + 1] == PREMIO_ESPECIAL || matrizTableroP[Pacman_x][1] == PREMIO_ESPECIAL){
                    punteo += 15;
                    cantidadPremios--;
                } else if(matrizTableroP[Pacman_x][Pacman_y + 1] == PREMIO_SIMPLE || matrizTableroP[Pacman_x][1] == PREMIO_SIMPLE){
                    punteo += 10;
                    cantidadPremios--;
                } else if( matrizTableroP[Pacman_x][Pacman_y + 1] == FANTASMA){
                    vidas--;
                }
                break;
            case IZQUIERDA:
                if(matrizTableroP[Pacman_x][Pacman_y - 1] == PREMIO_ESPECIAL || matrizTableroP[Pacman_x][nColumnas - 2] == PREMIO_ESPECIAL){
                    punteo += 15;
                    cantidadPremios--;
                } else if(matrizTableroP[Pacman_x][Pacman_y - 1] == PREMIO_SIMPLE || matrizTableroP[Pacman_x][nColumnas - 2] == PREMIO_SIMPLE){
                    punteo += 10;
                    cantidadPremios--;
                } else if(matrizTableroP[Pacman_x][Pacman_y - 1] == FANTASMA){
                    vidas--;
                }
                break;
        }
    }
    public static int calcularPremios(){
       cantidadPremios = (int)Math.round(((nFilas-2) * (nColumnas-2))*(opciones_premios/100.0));
       return cantidadPremios;
    }
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
            int numeroRan_x = (int)(ran.nextDouble()*(nFilas-2));
            int numeroRan_y = (int)(ran.nextDouble()*(nColumnas-2));
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
        int porcentajeParedes = (int)Math.round(((nFilas-2) * (nColumnas-2))*(opciones_paredes/100.0));

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
        int porcentajeFantasmas = (int)Math.round(((nFilas-2) * (nColumnas-2))*(opciones_trampas/100.0));
        System.out.println(porcentajeFantasmas);
        for(int f = 0 ; f < porcentajeFantasmas; f++){
            int numeroRan_x = (int)(ran.nextDouble()*nFilas);
            int numeroRan_y = (int)(ran.nextDouble()*nColumnas);

            for (int i = 0; i < nFilas; i++){
                for(int j = 0; j < nColumnas; j++){
                    if(matrizTableroP[numeroRan_x][numeroRan_y] == null && matrizTableroP[numeroRan_x][numeroRan_y] != "|" && matrizTableroP[numeroRan_x][numeroRan_y] != "_" && matrizTableroP[numeroRan_x][numeroRan_y] != PREMIO_ESPECIAL && matrizTableroP[numeroRan_x][numeroRan_y] != PREMIO_SIMPLE && matrizTableroP[numeroRan_x][numeroRan_y] != PARED){
                        matrizTableroP[numeroRan_x][numeroRan_y] = FANTASMA;

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
    public static void main(String ...args){
        menuPrincipal();
    }
}