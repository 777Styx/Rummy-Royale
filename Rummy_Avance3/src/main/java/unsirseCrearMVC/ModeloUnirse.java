package unsirseCrearMVC;

import entidades.Juego;
import java.util.Observable;

/**
 *
 * @author julli
 */
public class ModeloUnirse extends Observable {

    private Juego juego = null;

    public void crearJuego() {
        if (juego != null) {
            System.out.println("Ya esta hecho pana");
        } else {
            juego = new Juego();

        }
        System.out.println("modelo");
        setChanged();
        notifyObservers(juego);
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

}
