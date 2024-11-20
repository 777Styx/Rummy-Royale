package serverCarlos;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author julli
 */
public class Controlador implements Observer {

    private static ExpertoJuego expertJuego = new ExpertoJuego();

    public Controlador() {

    }

    public void crearJuego() {
        expertJuego.crearJuego();
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
