package partidaMVC;

import dtos.CombinacionDTO;
import dtos.FichaDTO;
import dtos.JugadorDTO;
import dtos.TableroDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 *
 * @author puerta
 */
public class ModeloJuego extends Observable {

    private ArrayList<JugadorDTO> jugadoresDTO;
    private ArrayList<FichaDTO> fichasDTO;
    private TableroDTO tableroDTO;
    private int indiceJugadorActual;
    private Random random;
    private static ModeloJuego instance;

    private ModeloJuego() {

        jugadoresDTO = new ArrayList<>();
        fichasDTO = new ArrayList<>();
        tableroDTO = new TableroDTO();
        indiceJugadorActual = 0;
        random = new Random();
    }

    public static ModeloJuego getInstance() {
        return instance == null ? (instance = new ModeloJuego()) : instance;
    }

    // Añadir un jugador al juego
    public void agregarJugador(JugadorDTO jugadorDTO) {
        if (jugadoresDTO.size() < 4) {
            jugadoresDTO.add(jugadorDTO);
            setChanged();
            notifyObservers("JUGADOR_REGISTRADO");
        }
    }
}
