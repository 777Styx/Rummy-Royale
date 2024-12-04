package partidaMVC;

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
    private VistaJuego view;

    /**
     * Constructor
     *
     * @param modeloJuego
     */
    public ControladorJuego(ModeloJuego modeloJuego) {
        this.modeloJuego = modeloJuego;
    }

    public static ControladorJuego getInstance(ModeloJuego modelojuego) {
        return controlador == null ? controlador = new ControladorJuego(modeloJuego) : controlador;
    }

    public void setModel(ModeloJuego model) {
        this.modeloJuego = modeloJuego;
    }
    
    public void setView(VistaJuego view) {
        this.view = view;
    }

    public void solicitarInicio() {
        modeloJuego.solicitarInicio();
    }
}
