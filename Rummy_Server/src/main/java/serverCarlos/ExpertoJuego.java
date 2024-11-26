package serverCarlos;

import entidades.Juego;

/**
 *
 * @author julli
 */
public class ExpertoJuego {

    Juego juego = Juego.getInstance();

    public ExpertoJuego() {

    }

    public void crearJuego() {

        System.out.println("Creando juego en experto");
        juego.setPartidaActiva(true);
    }

    public void isEmpty() {
        //Juego juego = juego.getInstance();
        //return juego.getJugadores().size();
    }

    public boolean hasSpace() {
        return juego.getJugadores().size() > 3;
    }
}
