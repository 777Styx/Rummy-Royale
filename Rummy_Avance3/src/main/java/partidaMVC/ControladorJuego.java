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
    private ModeloJuego modeloJuego;
    // Vista que presenta la interfaz de usuario
    private VistaJuego view;

    
    
    /**
     * Constructor
     * @param modeloJuego 
     */
    public ControladorJuego(ModeloJuego modeloJuego){
           this.modeloJuego = modeloJuego; 
    }
    
    /**
     * Obtiene la instancia del controlador (singleton)
     *
     * @return la instancia del controlador
     */
    
    //lo puse en comentarios porque no me dejaba tener el constructor de arriba
    
                        //    public static ControladorJuego getInstance() {
                        //        return controlador == null ? controlador = new ControladorJuego() : controlador;
                        //    }

    /**
     * Establece el modelo para el controlador.
     *
     * @param model el modelo a establecer
     */
    public void setModel(ModeloJuego model) {
        this.modeloJuego = modeloJuego;
    }

    /**
     * Establece la vista para el controlador.
     *
     * @param view la vista a establecer
     */
    public void setView(VistaJuego view) {
        this.view = view;
    }
}
