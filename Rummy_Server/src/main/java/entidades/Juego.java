package entidades;

import java.util.ArrayList;
import java.util.Observable;
import serverCarlos.Controlador;

/**
 *
 * @author julli
 */
public class Juego extends Observable {

    private ArrayList<Jugador> jugadores;
    private Tablero tablero;
    private boolean partidaActiva = false;
    private static Juego instance;

    private Juego() {
        this.jugadores = new ArrayList<>();
        this.partidaActiva = false;
    }

    public static synchronized Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
            System.out.println("Nueva instancia de Juego creada.");
        }
        return instance;
    }

    public boolean validarAvatarNoUsado(String avatar) {
        for (Jugador jugador : jugadores) {
            if (jugador.getAvatar().equals(avatar)) {
                return false;
            }
        }
        return true;
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public boolean isPartidaActiva() {
        return partidaActiva;
    }

    public synchronized void setPartidaActiva(boolean flag) {
        if (partidaActiva == true) {
            setChanged();
            notifyObservers("YA_CREADO");
        } else {
            this.partidaActiva = flag;
            setChanged();
            String estado = "CREADO";
            notifyObservers(estado);
        }
    }

}
