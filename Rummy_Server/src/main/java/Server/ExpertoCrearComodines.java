package Server;

import entidades.FichaComodin;
import entidades.IFicha;
import entidades.Juego;
import java.util.ArrayList;
import java.util.List;
import mensajes.Mensaje;
import mensajes.ReqConfigurarPartida;

/**
 *
 * @author carlo
 */
public class ExpertoCrearComodines implements Experto {

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
        System.out.println("estoy creando comodines");
        ReqConfigurarPartida req = (ReqConfigurarPartida) mensaje;
        int numComodines = req.getJuego().getNumComodines();
        List<IFicha> comodines = new ArrayList<>();

        while (comodines.size() < numComodines) {
            comodines.add(new FichaComodin());
        }

        juego.setComodines(comodines);
        juego.setConfigurado(false, mensaje);

    }

}
