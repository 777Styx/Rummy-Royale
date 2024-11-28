package serverCarlos;

import dtos.JuegoDTO;
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
    
    public void configurarPartida(JuegoDTO juego) {
        
    }

    public boolean hasSpace() {
        return juego.getJugadores().size() > 3;
    }
}
