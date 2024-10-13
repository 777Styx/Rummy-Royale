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
public class ModeloJuego implements Observable<ModeloObservador> {

    private ArrayList<Jugador> jugadores;
    private ArrayList<Ficha> fichas;
    private Tablero tablero;
    private int indiceJugadorActual;
    private Random random;
    private ColorFicha color;
    private List<ModeloObservador> observadores;

    public ModeloJuego() {
        jugadores = new ArrayList<>();
        fichas = new ArrayList<>();
        tablero = new Tablero();
        indiceJugadorActual = 0;
        random = new Random();
        // inicializarFichas();
        // barajarFichas();
    }

    /**
     * Reparte las fichas iniciales para cada jugador
     *
     * @param cantidad: numero de fichas que se le dará a cada jugador
     */
    public void repartirFichasIniciales(int cantidad) {
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < cantidad; i++) {
                Ficha ficha = tomarFicha(); // Llama al método tomarFicha()
                if (ficha != null) { // Verifica si se pudo tomar una ficha
                    jugador.agregarFicha(ficha);
                } else {
                    // Si no hay más fichas, puedes decidir qué hacer (e.g., lanzar una excepción o salir del bucle)
                    System.out.println("No hay suficientes fichas para repartir.");
                    return; // Termina el método si no hay más fichas
                }
            }
        }
        notificarObservadores(); // Notifica después de repartir fichas
    }

    // Mezcla las fichas aleatoriamente
    private void barajarFichas() {
        Collections.shuffle(fichas, random);
    }

    // Añadir un jugador al juego
    public void agregarJugador(Jugador jugador) {
        if (jugadores.size() < 4) { // Máximo de 4 jugadores
            jugadores.add(jugador);
            notificarObservadores(); // Notifica al agregar un jugador
        }
    }

    // Obtener el jugador actual
    public Jugador obtenerJugadorActual() {
        return jugadores.get(indiceJugadorActual);
    }

    // Pasar al siguiente jugador
    public void siguienteTurno() {
        indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
        notificarObservadores(); // Notifica al pasar el turno
    }

    /**
     *
     */
    public void tomarFicha() {
        if (fichas.isEmpty()) {
            System.out.println("No hay fichas pana");
            return;
        }
        Jugador jugador = obtenerJugadorActual();
        jugador.agregarFicha(fichas.remove(0));

        // jugador. fichas.remove(0);
        // return fichas.remove(random.nextInt(fichas.size()));
    }

    // Colocar una combinación en el tablero
    public void colocarCombinacionEnTablero(Combinacion combinacion) {
        if (combinacion != null && combinacion.esValida()) {
            tablero.agregarCombinacion(combinacion);
            notificarObservadores(); // Notifica al colocar una combinación
        } else {
            //Poner el error
        }
    }

    /**
     *
     * @return
     */
    public List<Combinacion> obtenerCombinaciones() {
        return tablero.obtenerCombinaciones(); // Devuelve las combinaciones del tablero
    }

    /**
     * Verificar si el juego ha terminado (cuando un jugador pone todas sus
     * fichas)
     *
     * @return boolean indicando si el juego termina o continúa
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

    // Obtener al jugador ganador (el que se quedó sin fichas)
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

    void colocarFichasEnTablero(List<Ficha> fichas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
