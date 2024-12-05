package expertos;

import entidades.Juego;
import mensajes.Mensaje;

/**
 *
 * @author carlo
 */
public class ExpertoRepartirFichas implements Experto {

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
        juego.repartirFichas();
        juego.setPartidaEmpezada(false, mensaje);
    }
    
}
