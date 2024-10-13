package tableMVC;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author puerta
 */
public class ModeloTablero implements Observable<ModeloObservador> {

    private ArrayList<Jugador> jugadores;
    private ArrayList<Ficha> fichas;
    private ArrayList<Ficha> tablero;
    private int indiceJugadorActual;
    private Random random;
    private ColorFicha color;
    private List<ModeloObservador> observadores;

    public ModeloTablero() {
        jugadores = new ArrayList<>();
        fichas = new ArrayList<>();
        tablero = new ArrayList<>();
        indiceJugadorActual = 0;
        random = new Random();
        inicializarFichas();
        barajarFichas();
    }

    /**
     * Reparte las fichas iniciales para cada jugador
     *
     * @param cantidad: numero de fichas que se le dara a cada jugador
     */
    public void repartirFichasIniciales(int cantidad) {
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < cantidad; i++) {
                jugador.agregarFicha(tomarFicha());
            }
        }
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
            //Hay qeu poner un error
            return;
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
            if (jugador.obtenerTamanoDeMano() == 0) {
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
            if (jugador.obtenerTamanoDeMano() == 0) {
                return jugador;
            }
        }
        // No hay ganador aún
        return null;
    }

    @Override
    public void agregarObservador(ModeloObservador t) {
        observadores.add(t);
    }

    @Override
    public void removerObservador(ModeloObservador t) {
        observadores.remove(t);
    }

    @Override
    public void notificarObservadores() {
        for (ModeloObservador o : observadores) {

        }
    }

}
