package serverCarlos;

import entidades.Juego;

/**
 *
 * @author julli
 */
public class ExpertoJuego {

    public ExpertoJuego() {

    }

    public void crearJuego() {
        Juego juego = Juego.getInstance();
        System.out.println("Creando juego en experto");
        juego.setPartidaActiva(true);
    }
    
    public void isEmpty() {
        //Juego juego = juego.getInstance();
        //return juego.getJugadores().size();
    }
}
