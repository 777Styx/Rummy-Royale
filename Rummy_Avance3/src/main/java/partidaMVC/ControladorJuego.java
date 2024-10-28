package partidaMVC;

import entidades.IFicha;
import entidades.Jugador;
import java.util.List;

/**
 *
 * @author puerta
 */
public class ControladorJuego {

    // Instancia del controlador (Singleton)
    private static ControladorJuego controlador;
    // Modelo que contiene la logica del juego
    private ModeloJuego model;
    // Vista que presenta la interfaz de usuario
    private VistaJuego view;

    /**
     * Obtiene la instancia del controlador (singleton)
     *
     * @return la instancia del controlador
     */
    public static ControladorJuego getInstance() {
        return controlador == null ? controlador = new ControladorJuego() : controlador;
    }

    /**
     * Establece el modelo para el controlador.
     *
     * @param model el modelo a establecer
     */
    public void setModel(ModeloJuego model) {
        this.model = model;
    }

    /**
     * Establece la vista para el controlador.
     *
     * @param view la vista a establecer
     */
    public void setView(VistaJuego view) {
        this.view = view;
    }

//    /**
//     * Agrega un jugador
//     *
//     * @param nombre: nombre del jugador
//     */
//    public void agregarJugador(String nombre, String avatar) {
//        Jugador jugador = new Jugador(nombre, avatar);
//        model.agregarJugador(jugador);
//    }
    /**
     * Iniciar el juego y repartir fichas iniciales
     *
     * @param cantidad Cantidad de fichas a repartir
     */
    public void iniciarJuego(int cantidad) {
        model.repartirFichasIniciales(cantidad);
    }

    /**
     * toma una ficha aleatora del ModeloJuego
     */
    public void tomarFicha() {
        model.tomarFicha();
    }

    /**
     * Coloca un conjunto de fichas en el tablero. Verifica que al menos tres
     * fichas sean proporcionadas.
     *
     * @param fichas la lista de fichas a colocar en el tablero
     */
    public void colocarFichas(List<IFicha> fichas) {
        // Verificar que la lista de fichas tenga al menos 3 elementos
        if (fichas.size() < 3) {

            return;
        }
        //Colocar las fichas en el tablero
     //   model.colocarFichasEnTablero(fichas);

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
