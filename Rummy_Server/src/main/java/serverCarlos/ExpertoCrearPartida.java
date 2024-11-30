package serverCarlos;

import entidades.Juego;
import mensajes.Mensaje;

/**
 *
 * @author carlo
 */
public class ExpertoCrearPartida implements Experto {

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
        juego.setPartidaActiva(true);
    }
    
}
