package serverCarlos;

import dtos.JuegoDTO;
import entidades.Juego;
import entidades.Mazo;

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

    public boolean hasSpace() {
        return juego.getJugadores().size() < 3;
    }
    
    public void configurarPartida(Mazo mazo) {
        juego.setMazo(mazo);
    }
}
