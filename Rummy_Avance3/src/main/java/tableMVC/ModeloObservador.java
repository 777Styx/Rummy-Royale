package tableMVC;

import java.util.List;

/**
 *
 * @author puerta
 */
public interface ModeloObservador {

    /**
     * Notidicar la actualizacion del tablero
     *
     * @param tablero: nuevo estado del tablero
     */
    void actualizarTablero(List<Ficha> tablero);

    /**
     * Notificar quien es el jugador en turno
     *
     * @param jugador el jugador que ahora tiene el turno
     */
    void actualizarJugadorActual(Jugador jugador);

    /**
     * Notificar la finalizacion del juego
     *
     * @param ganador el jugador que ganó el juego
     */
    void juegoTerminado(Jugador ganador);
}
