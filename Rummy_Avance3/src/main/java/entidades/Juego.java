package entidades;

import java.util.ArrayList;

/**
 *
 * @author julli
 */
public class Juego {

    private ArrayList<Jugador> jugadores;
    private Tablero tablero;
    private boolean partidaActiva = false;

    public Juego() {
        this.jugadores = new ArrayList<>();
        this.partidaActiva = false;
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
}
