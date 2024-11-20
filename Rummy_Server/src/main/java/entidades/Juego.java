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

    private static Controlador controlador = new Controlador();

    private Juego() {
        this.jugadores = new ArrayList<>();
        this.partidaActiva = false;
    }

    public static Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
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

    public void setPartidaActiva(boolean partidaActiva) {
        this.partidaActiva = partidaActiva;
    }

}
