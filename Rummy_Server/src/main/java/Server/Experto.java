package Server;

import entidades.Juego;
import mensajes.Mensaje;

/**
 *
 * @author carlo
 */
public interface Experto {
    void ejecutar(Juego juego, Mensaje mensaje);
}
