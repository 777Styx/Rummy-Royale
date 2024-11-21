package partidaMVC;

import dto.CombinacionDTO;
import dto.FichaDTO;
import dto.JugadorDTO;
import dto.TableroDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 *
 * @author puerta
 */
public class ModeloJuego extends Observable{

    private ArrayList<JugadorDTO> jugadoresDTO;
    private ArrayList<FichaDTO> fichasDTO;
    private TableroDTO tableroDTO;
    private int indiceJugadorActual;
    private Random random;

    public ModeloJuego() {

        jugadoresDTO = new ArrayList<>();
        fichasDTO = new ArrayList<>();
        tableroDTO = new TableroDTO();
        indiceJugadorActual = 0;
        random = new Random();
        // inicializarFichas();
        // barajarFichas();
    }

    /**
     * Reparte las fichas iniciales para cada jugador
     *
     * @param cantidad: numero de fichas que se le dará a cada jugador
     */
    public void repartirFichasIniciales(int cantidad) {
        for (JugadorDTO jugador : jugadoresDTO) {
            for (int i = 0; i < cantidad; i++) {
                FichaDTO fichaDTO = darFicha(); // Llama al método tomarFicha()
                if (fichaDTO != null) { // Verifica si se pudo tomar una ficha
                    jugador.agregarFicha(fichaDTO);
                } else {
                    // Si no hay más fichas, puedes decidir qué hacer (e.g., lanzar una excepción o salir del bucle)
                    System.out.println("No hay suficientes fichas para repartir.");
                    return; // Termina el método si no hay más fichas
                }
            }
        }
        // notificarObservadores(); // Notifica después de repartir fichas
    }

    /**
     * Toma una ficha del conjunto de fichas disponibles y la devuelve.
     *
     * @return La ficha tomada, o null si no quedan fichas.
     */
    public FichaDTO darFicha() {
        if (fichasDTO.isEmpty()) {
            System.out.println("No hay fichas pana");
            return null; // Devuelve null si no hay más fichas
        }
        return fichasDTO.remove(0); // Devuelve la ficha tomada
    }

    // Mezcla las fichas aleatoriamente
    private void barajarFichas() {
        Collections.shuffle(fichasDTO, random);
    }

    // Añadir un jugador al juego
    public void agregarJugador(JugadorDTO jugadorDTO) {
        if (jugadoresDTO.size() < 4) { // Máximo de 4 jugadores
            jugadoresDTO.add(jugadorDTO);
            //  notificarObservadores(); // Notifica al agregar un jugador
        }
    }

    // Obtener el jugador actual
    public JugadorDTO obtenerJugadorActual() {
        return jugadoresDTO.get(indiceJugadorActual);
    }

    // Pasar al siguiente jugador
    public void siguienteTurno() {
        indiceJugadorActual = (indiceJugadorActual + 1) % jugadoresDTO.size();
        // notificarObservadores(); // Notifica al pasar el turno
    }

    /**
     *
     */
    public void tomarFicha() {
        if (fichasDTO.isEmpty()) {
            System.out.println("No hay fichas pana");
            return;
        }
        JugadorDTO jugadorDTO = obtenerJugadorActual();
        jugadorDTO.agregarFicha(fichasDTO.remove(0));

        // jugador. fichas.remove(0);
        // return fichas.remove(random.nextInt(fichas.size()));
    }

    // Colocar una combinación en el tablero
    public void colocarCombinacionEnTablero(CombinacionDTO combinacionDTO) {
        if (combinacionDTO != null && combinacionDTO.esValida()) {
            tableroDTO.agregarCombinacion(combinacionDTO);
            //  notificarObservadores(); // Notifica al colocar una combinación
        } else {
            //Poner el error
        }
    }

    /**
     *
     * @return
     */
    public List<CombinacionDTO> obtenerCombinaciones() {
        return tableroDTO.obtenerCombinaciones(); // Devuelve las combinaciones del tablero
    }

    /**
     * Verificar si el juego ha terminado (cuando un jugador pone todas sus
     * fichas)
     *
     * @return boolean indicando si el juego termina o continúa
     */
//    public boolean verificarFinDelJuego() {
//        for (JugadorDTO jugadorDTO : jugadoresDTO) {
//            if (jugadorDTO.obtenerTamanoDeMano() == 0) {
//                // Juego terminado
//                return true;
//            }
//        }
//        // El juego sigue
//        return false;
//    }

    // Obtener al jugador ganador (el que se quedó sin fichas)
//    public JugadorDTO obtenerGanador() {
//        for (JugadorDTO jugadorDTO : jugadoresDTO) {
//            if (jugadorDTO.obtenerTamanoDeMano() == 0) {
//                return jugadorDTO;
//            }
//        }
//        // No hay ganador aún
//        return null;
//    }

}
