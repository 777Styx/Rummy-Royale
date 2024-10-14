package registroMVC;

import entidades.ColorFicha;
import entidades.Jugador;
import java.util.ArrayList;

/**
 *
 * @author puerta
 */
public class modelo {

    private ArrayList<Jugador> jugadores;

    public modelo() {
        jugadores = new ArrayList<>();
    }

    /**
     * Se registra un jugador
     *
     * @param jugador: jugador que se unira al juego
     * @return
     */
    public boolean registrarJugador(Jugador jugador) {
        if (jugadores.size() >= 4) {
            System.out.println("Aqui no pana ya ta' lleno");
            return false;
        }

        jugadores.add(jugador);
        return true;
    }

    public int obtenerCantidadJugadores() {
        return jugadores.size();
    }

}
