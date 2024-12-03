package partidaMVC;

import actualizaciones.JugadoresActualizados;
import dtos.CombinacionDTO;
import dtos.FichaDTO;
import dtos.JugadorDTO;
import dtos.TableroDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import mensajes.Mensaje;
import mensajes.ResRegistroJugador;

/**
 *
 * @author puerta
 */
public class ModeloJuego extends Observable {

    private List<JugadorDTO> jugadores;
    private List<FichaDTO> fichasDTO;
    private TableroDTO tableroDTO;
    private int indiceJugadorActual;
    private Random random;
    private static ModeloJuego instance;

    private ModeloJuego() {

        jugadores = new ArrayList<>();
        fichasDTO = new ArrayList<>();
        tableroDTO = new TableroDTO();
        indiceJugadorActual = 0;
        random = new Random();
    }

    public static ModeloJuego getInstance() {
        return instance == null ? (instance = new ModeloJuego()) : instance;
    }

    public void actualizarJugadores(Mensaje mensaje) {
        ResRegistroJugador res = (ResRegistroJugador) mensaje;
        this.jugadores = res.getJugadores();
        setChanged();
        notifyObservers(new JugadoresActualizados(this.jugadores));
    }
}
