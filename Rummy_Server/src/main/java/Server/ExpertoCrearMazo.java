package Server;

import entidades.IFicha;
import entidades.Juego;
import entidades.Mazo;
import java.util.List;
import mensajes.Mensaje;

/**
 *
 * @author carlo
 */
public class ExpertoCrearMazo implements Experto {

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
        List<IFicha> fichasNumericas = juego.getFichasNumericas();
        List<IFicha> comodines = juego.getComodines();

        Mazo mazo = new Mazo(fichasNumericas, comodines);
        juego.setMazo(mazo);
    }
    
}
