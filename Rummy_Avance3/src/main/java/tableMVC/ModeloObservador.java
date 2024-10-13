package tableMVC;

import java.util.List;

/**
 *
 * @author puerta
 */
public interface ModeloObservador {

    /**
     * Notifica la actualizacion del estado del tablero.
     *
     * @param tablero: lista de fichas que representa el nuevo estado del
     * tablero.
     */
    void actualizarTablero(List<Ficha> tablero);

    /**
     * Notifica quién es el jugador en turno.
     *
     * @param jugador: jugador que ahora tiene el turno.
     */
    void actualizarJugadorActual(Jugador jugador);

    /**
     * Notifica la finalización del juego.
     *
     * @param ganador El jugador que ganó el juego.
     */
    void juegoTerminado(Jugador ganador);

    /**
     * Notifica que un jugador ha tomado una ficha.
     *
     * @param jugador El jugador que ha tomado la ficha.
     * @param ficha La ficha que fue tomada por el jugador.
     */
    void fichaTomada(Jugador jugador, Ficha ficha);

    /**
     * Notifica que se ha agregado un nuevo jugador al juego.
     *
     * @param jugador El jugador que ha sido agregado.
     */
    void jugadorAgregado(Jugador jugador);

    /**
     * Notifica el cambio de turno entre los jugadores.
     *
     * @param jugador El jugador que ahora tiene el turno.
     */
    void cambioTurno(Jugador jugador);
}
