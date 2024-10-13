package tableMVC;

import java.util.List;

/**
 *
 * @author puerta
 */
public class Controller {

    // Instancia del controlador (Singleton)
    private static Controller instance;
    // Modelo que contiene la logica del juego
    private ModeloTablero model;
    // Vista que presenta la interfaz de usuario
    private View view;

    /**
     * Obtiene la instancia del controlador (singleton)
     *
     * @return la instancia del controlador
     */
    public static Controller getInstance() {
        return instance == null ? instance = new Controller() : instance;
    }

    /**
     * Establece el modelo para el controlador.
     *
     * @param model el modelo a establecer
     */
    public void setModel(ModeloTablero model) {
        this.model = model;
    }

    /**
     * Establece la vista para el controlador.
     *
     * @param view la vista a establecer
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * Agrega un jugador
     *
     * @param nombre: nombre del jugador
     */
    public void agregarJugador(String nombre, String avatar) {
        Jugador jugador = new Jugador(nombre, avatar);
        model.agregarJugador(jugador);
    }

    /**
     * Iniciar el juego y repartir fichas iniciales
     *
     * @param cantidad Cantidad de fichas a repartir
     */
    public void iniciarJuego(int cantidad) {
        model.repartirFichasIniciales(cantidad);
    }

    /**
     * Coloca un conjunto de fichas en el tablero. Verifica que al menos tres
     * fichas sean proporcionadas.
     *
     * @param fichas la lista de fichas a colocar en el tablero
     */
    public void colocarFichas(List<Ficha> fichas) {
        // Verificar que la lista de fichas tenga al menos 3 elementos
        if (fichas.size() < 3) {

            return;
        }
        //Colocar las fichas en el tablero
        model.colocarFichasEnTablero(fichas);

    }

    /**
     * Avanza al siguiente turno del juego.
     */
    public void siguienteTurno() {
        model.siguienteTurno();

    }

    /**
     * Verifica si el juego ha terminado. Si un jugador ha ganado, muestra el
     * ganador en la vista.
     */
    public void verificarFinDelJuego() {
        if (model.verificarFinDelJuego()) {
            Jugador ganador = model.obtenerGanador();
        }
    }
}