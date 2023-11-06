/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package goldenaxe;

import java.util.Scanner;

/**
 *
 * @author MEDAC
 */
public class GoldenAxe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numJugadores;
        String tipo;
        Scanner entrada = new Scanner(System.in);

        //Selección de número de jugadores
        System.out.println("ELIGE EL NUMERO DE JUGADORES DE 1 A 5: ");
        numJugadores = entrada.nextInt();
        Jugador[] jugadores = new Jugador[numJugadores]; //Creamos un array con el numero de jugadores indicado

        //Selección de personajes
        for (int i = 0; i < jugadores.length; i++) {
            System.out.println("Selecciona el jugador: ENANO, GUERRERO O AMAZONA"); //Elegimos el tipo de jugador y lo definimos en el array
            tipo = entrada.next();
            if (tipo.equalsIgnoreCase("enano")) {
                jugadores[i] = new Enano();
            } else if (tipo.equalsIgnoreCase("guerrero")) {
                jugadores[i] = new Guerrero();
            } else {
                jugadores[i] = new Amazona();
            }
        }

        //Comienza el juego
        Cthulhu enemigo = new Cthulhu(20 + numJugadores * 2); //Declaro el enemigo
        int turno = 0;
        boolean muerto = false;
        do {

            for (int i = 0; i < jugadores.length; i++) {
                int accion;
                System.out.println("TURNO " + turno);
                System.out.println("Jugador: " + jugadores[i].getNombre() + " Elige qué quieres hacer en tu turno: "); //Preguntamos al jugador la accion a realizar
                do {
                    System.out.println("1: Luchar con Cthulhu 2: Coger una carta 3: Descansar");
                    accion = entrada.nextInt();
                    switch (accion) { //Utilizamos un switch para que el programa ejecute la acción deseada
                        case 1:
                            lucharCthulhu(jugadores[i], enemigo);
                            break;
                        case 2:
                            cogerCarta(jugadores[i]);
                            break;
                        case 3:
                            reponerVida(jugadores[i]);
                            break;
                        default:
                            System.out.println("Valor incorrecto. Inténtalo de nuevo"); //Si se da un valor distinto de 1,2,3 se le pide al usuario que indique el valor de nuevo
                    }
                } while (accion < 1 || accion > 3);
                if ((jugadores[i].getEstaVivo() == false) || (enemigo.getEstaVivo() == false)) {
                    muerto = true;
                    break;
                } //En caso de estar muerto un jugador o el enemigo, se pone la variable muerto como true, esta nos permite salir del do-while principal
            }
            turno++; //Vamos aumentando la variable turno para llevar un recuento de los mismos
        } while ((turno <= 8 - numJugadores) && !muerto);

        if (enemigo.getVida() == 0) { //Le indicamos al jugador si ha ganado o perdido la partida
            System.out.println("Has matado a Cthulhu y has ganado la partida");
        } else {
            System.out.println("Has perdido la partida.");
        }
    }

    public static void lucharCthulhu(Jugador jugador, Cthulhu enemigo) {
        int suma = 0;
        int dado;
        for (int i = 0; i < jugador.getFuerza(); i++) {
            dado = (int) (Math.random() * 6) + 1; //Generamos dados aleatorios según la cantidad de vida que tiene el jugador
            System.out.print("Tirada del dado " + i + ": " + dado + "\n");
            suma += dado; //Sumamos los resultados para tener el daño final que se le realiza al enemigo
        }
        System.out.println("La suma de los dados es: " + suma);
        System.out.println("Vida de Cthulhu: " + enemigo.getVida());
        enemigo.setVida(enemigo.getVida() - suma); // Le restamos la vida al enemigo y comprobamos si ha muerto o no
        if (enemigo.getVida() <= 0) {
            enemigo.setVida(0);
            enemigo.setEstaVivo(false); //En caso de estar muerto, se cambia el valor de "EstaVivo" y se avisa al jugador
            System.out.println("Has matado a Cthulhu");
        } else {
            System.out.println("Vida de Cthulhu: " + enemigo.getVida());
        }

        jugador.setVida(jugador.getVida() - 1); //Le restamos 1 de vida al jugador cada vez que pelea con el Cthulhu y comprobamos si muerte
        if (jugador.getVida() <= 0) {
            jugador.setVida(0);
            System.out.println("El jugador ha muerto.");
            jugador.setEstaVivo(false);
        } else {
            System.out.println("Vida del jugador ahora es: " + jugador.getVida());
        }
    }

    public static void cogerCarta(Jugador jugador) {
        int numeroCarta = (int) (Math.random() * 4); //Generamos un numero aleatorio con la cantidad de cartas que tenemos para elegir una
        Carta carta = jugador.getCarta(numeroCarta); //Cogemos la carta del array de la clase Jugador
        System.out.println(carta.getFraseCarta()); 
        //Dependiendo de las características de la carta ejecutará una acción:
        if (carta.getVida() == 1) {
            jugador.setVida(jugador.getVida() + 1); //Damos +1 de vida
            System.out.println("Vida del jugador: " + jugador.getVida());
        }
        if (carta.getVida() == 2) {
            jugador.setVida(jugador.getVida() + 2); //Damos +2 de vida
            System.out.println("Vida del jugador: " + jugador.getVida());
        }
        if (carta.getVida() == -1) {
            jugador.setVida(jugador.getVida() - 1); //Restamos -1 de vida y comprobamos si muere o no
            System.out.println("Vida del jugador: " + jugador.getVida());
            if (jugador.getVida() == 0) {
                System.out.println("El jugador ha muerto.");
                jugador.setEstaVivo(false);
            }
        }
        if (carta.getFuerza() == 1) {
            jugador.setFuerza(jugador.getFuerza() + 1); //Damos +1 fuerza
            System.out.println("Fuerza del jugador: " + jugador.getFuerza());
        }
        if (carta.getFuerza() == -1) {
            jugador.setFuerza(jugador.getFuerza() - 1); //Restamos -1 fuerza
            System.out.println("Fuerza del jugador: " + jugador.getFuerza());
        }

    }

    public static void reponerVida(Jugador jugador) {
        //Si el jugador selecciona descansar, le sumamos 1 de vida y se lo indicamos
        jugador.setVida(jugador.getVida() + 1);
        System.out.println("Tu vida ha aumentado en 1");
        System.out.println("Vida del jugador " + jugador.getVida());
    }
}
