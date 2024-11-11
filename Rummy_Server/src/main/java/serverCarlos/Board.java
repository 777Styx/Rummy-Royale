package serverCarlos;

/**
 *
 * @author julli
 */
public class Board {

    private static Controlador controlador = new Controlador();

    protected Board() {

    }

    public void ponerJuego() {
        controlador.ponerJuego();

//        setChanged();
//        notify();
    }

}
