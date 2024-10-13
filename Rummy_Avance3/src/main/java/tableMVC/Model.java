package tableMVC;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author puerta
 */
public class Model implements Observable<ModeloObservador> {

    private ArrayList<Jugador> jugadores;
    private ArrayList<Ficha> fichas;
    private ArrayList<Ficha> tablero;
    private int indiceJugadorActual;
    private Random random;
    private ColorFicha color;

    public ModeloJuegoRummy() {
        jugadores = new ArrayList<>();
        fichas = new ArrayList<>();
        tablero = new ArrayList<>();
        indiceJugadorActual = 0;
        random = new Random();
        inicializarFichas();
        barajarFichas();
    }

    // Inicializa las fichas del juego (números del 1 al 13 en 4 colores y 2 comodines)
    private void inicializarFichas() {
        for (int i = 1; i <= 13; i++) {
            for (Color color : Color.values()) {
                if (color != Color.COMODIN) { // Evitar agregar color de comodín
                    fichas.add(new Ficha(i, color));
                    fichas.add(new Ficha(i, color)); // Dos sets de fichas
                }
            }
        }
        // Añadir comodines
        fichas.add(new Ficha(0, Color.COMODIN)); // Comodín
        fichas.add(new Ficha(0, Color.COMODIN)); // Comodín
    }

    // Mezcla las fichas aleatoriamente
    private void barajarFichas() {
        Collections.shuffle(fichas, random);
    }

    // Añadir un jugador al juego
    public void agregarJugador(Jugador jugador) {
        if (jugadores.size() < 4) { // Máximo de 4 jugadores
            jugadores.add(jugador);
        }
    }

    // Obtener el jugador actual
    public Jugador obtenerJugadorActual() {
        return jugadores.get(indiceJugadorActual);
    }

    // Pasar al siguiente jugador
    public void siguienteTurno() {
        indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
    }

    // Dar fichas iniciales a cada jugador (14 fichas)
    public void repartirFichasIniciales() {
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < 14; i++) {
                jugador.agregarFicha(tomarFicha());
            }
        }
    }

    // Tomar una ficha aleatoria del mazo
    public Ficha tomarFicha() {
        if (fichas.isEmpty()) {
            return null; // Si no hay más fichas disponibles
        }
        return fichas.remove(0);
    }

    // Colocar una ficha en el tablero
    public void colocarFichasEnTablero(List<Ficha> fichas) {
        if (fichas.size() < 3) {
            throw new IllegalArgumentException("Debes colocar al menos 3 fichas en el tablero.");
        }

        tablero.addAll(fichas);
    }

   /**
    * 
    * @return 
    */
    public List<Ficha> obtenerTablero() {
        return tablero;
    }

    /**
     * Verificar si el juego ha terminado (cuando un jugador pone todas sus
     * fichas)
     *
     * @return boolean indicando si el juega termina o continua
     */
    public boolean verificarFinDelJuego() {
        for (Jugador jugador : jugadores) {
            if (jugador.obtenerTamañoDeMano() == 0) {
                // Juego terminado
                return true;
            }
        }
        // El juego sigue
        return false;
    }

    // Obtener al jugador ganador (el que se quedo sin fichas)
    public Jugador obtenerGanador() {
        for (Jugador jugador : jugadores) {
            if (jugador.obtenerTamañoDeMano() == 0) {
                return jugador;
            }
        }
        // No hay ganador aún
        return null;
    }

    @Override
    public void agregarObservador(ModeloObservador t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removerObservador(ModeloObservador t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    private Deck deck;
//    public ModelolDeck() {
//        this.deck = Deck.getInstance();
//    }
//
//    public void generateChip() {
//        deck.drawChip();
//    }
//      private int number;
//    private String color;
//
//    public Chip(int numero, String color) {
//        this.number = numero;
//        this.color = color;
//    }
//
//    public int getNumero() {
//        return number;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    @Override
//    public String toString() {
//        return "Carta: " + number + " de " + color;
//    }
}
