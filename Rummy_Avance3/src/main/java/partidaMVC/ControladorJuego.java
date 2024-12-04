package partidaMVC;

import Cliente.Cliente;
import dtos.FichaDTO;
import dtos.JugadorDTO;
import java.util.List;

/**
 *
 * @author puerta
 */
public class ControladorJuego {

    // Instancia del controlador (Singleton)
    private static ControladorJuego controlador;
    // Modelo que contiene la logica del juego
    private static ModeloJuego modeloJuego;
    // Vista que presenta la interfaz de usuario
    private VistaJuego vistaJuego;

    /**
     * Constructor
     *
     * @param modeloJuego
     */
    public ControladorJuego() {

    }

    public ControladorJuego(ModeloJuego modeloJuego) {
        this.modeloJuego = modeloJuego;
    }

    public static ControladorJuego getInstance() {
        return controlador == null ? controlador = new ControladorJuego() : controlador;
    }

    public void inicializar(Cliente cliente) {
        this.modeloJuego = ModeloJuego.getInstance();
        this.modeloJuego.setCliente(cliente);
        this.vistaJuego = new VistaJuego(this.controlador);
        this.modeloJuego.addObserver(vistaJuego);
        this.vistaJuego.setVisible(true);
    }

    public void setModel(ModeloJuego model) {
        this.modeloJuego = modeloJuego;
    }

    public void setvistaJuego(VistaJuego vistaJuego) {
        this.vistaJuego = vistaJuego;
    }

    public void solicitarInicio() {
        modeloJuego.solicitarInicio();
    }
}
