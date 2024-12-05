package expertos;

import entidades.Juego;
import mensajes.Mensaje;

/**
 *
 * @author carlo
 */
public class ExpertoEmpezarPartida implements Experto{

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
        juego.empezarPartida();
        juego.setPartidaEmpezada(true, mensaje);
    }
    
}
