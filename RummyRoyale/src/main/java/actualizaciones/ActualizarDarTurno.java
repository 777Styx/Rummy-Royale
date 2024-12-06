package actualizaciones;

import dtos.JugadorDTO;

/**
 *
 * @author carlo
 */
public class ActualizarDarTurno implements ActualizacionJuego {

    public ActualizarDarTurno(JugadorDTO jugador) {
        this.jugador = jugador;
    }
    private JugadorDTO jugador;
    
    @Override
    public void aplicar(ViewJuego vista) {
        vista.actualizarDarTurno(jugador);
    }

    @Override
    public void aplicar(Vista vista) {
        if (vista instanceof ViewJuego) {
            aplicar((ViewJuego) vista);
        }
    }

}
