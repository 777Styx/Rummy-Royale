package tableMVC;

import entidades.IFicha;
import entidades.Tablero;
import entidades.Jugador;
import entidades.Combinacion;
import entidades.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author puerta
import entidades.Jugador;
import entidades.Combinacion;
 */
public class ModeloMesa implements IObservable<IModeloObservador> {

    private ArrayList<Jugador> jugadores;
    private ArrayList<IFicha> fichas;
    private Tablero tablero;
    private int indiceJugadorActual;
    private Random random;
    private List<IModeloObservador> observadores;

    public ModeloMesa() {
        observadores = new ArrayList<>();
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
                IFicha ficha = darFicha(); // Llama al método tomarFicha()
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

    /**
     * Toma una ficha del conjunto de fichas disponibles y la devuelve.
     *
     * @return La ficha tomada, o null si no quedan fichas.
     */
    public IFicha darFicha() {
        if (fichas.isEmpty()) {
            System.out.println("No hay fichas pana");
            return null; // Devuelve null si no hay más fichas
        }
        return fichas.remove(0); // Devuelve la ficha tomada
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
    public void agregarObservador(IModeloObservador t) {
        observadores.add(t);
    }

    @Override
    public void removerObservador(IModeloObservador t) {
        observadores.remove(t);
    }

    @Override
    public void notificarObservadores() {
        for (IModeloObservador observador : observadores) {
            observador.actualizarTablero(tablero.obtenerCombinaciones()); // Notifica el estado del tablero a las vistas.
            observador.actualizarJugadorActual(obtenerJugadorActual()); // Notifica el jugador actual a las vistas.
        }
    }

    void colocarFichasEnTablero(List<IFicha> fichas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
