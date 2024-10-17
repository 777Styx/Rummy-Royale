package registroMVC;

import java.awt.Color;

/**
 *
 * @author puerta
 */
public class ControladorRegistro {

    private static ControladorRegistro controlador;
    private ModeloRegistro modelo;
    private ViewRegistro view;

    public static ControladorRegistro getInsatnce() {
        return controlador == null ? controlador = new ControladorRegistro() : controlador;
    }

    // Método para mostrar la vista
    public void mostrarVista() {
        if (view != null) {
            view.setVisible(true); // Muestra la vista
        } else {
            System.out.println("La vista no está configurada.");
        }
    }

    public void crearJugador(String nombre, String avatar, Color color1, Color color2, Color color3, Color color4) {
        modelo.crearJugador(nombre, avatar, color1, color2, color3, color4);
    }

    public void setModelo(ModeloRegistro modelo) {
        this.modelo = modelo;
    }

    public void setView(ViewRegistro view) {
        this.view = view;
    }

}
