package tableMVC;

import java.awt.List;

/**
 *
 * @author puerta
 */
public class Controller {

    private static Controller instance;
    private Model model;
    private View view;

    public static Controller getInstance() {
        return instance == null ? instance = new Controller() : instance;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void agregarJugador(String nombre, ColorFicha color) {
        Jugador jugador = new Jugador(nombre, color);
        model.agregarJugador(jugador);
        // Actualiza la vista si es necesario
    }

    /**
     * Actualiza la vista para que se vea el estado inicial del juego
     */
    public void iniciarJuego() {
        model.repartirFichasIniciales();

    }

    public void colocarFichas(List<Ficha> fichas) {
        // Verificar que la lista de fichas tenga al menos 3 elementos
        if (fichas.size() < 3) {
          
            return;
        }

      //Colocar las fichas en el tablero
        for (Ficha ficha : fichas) {
            model.colocarFichaEnTablero(ficha);
        }

        // Actualizar la vista 
        view.actualizarTablero(model.obtenerTablero());
    }

    public void siguienteTurno() {
        model.siguienteTurno();
        // Actualiza la vista
    }

    public void verificarFinDelJuego() {
        if (model.verificarFinDelJuego()) {
            Jugador ganador = model.obtenerGanador();
            // Mostrar el ganador en la vista
        }
    }
}
