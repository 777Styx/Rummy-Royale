package registroMVC;

import java.awt.Color;

/**
 *
 * @author puerta
 */
public class Controlador {

    private static Controlador controlador;
    private Modelo modelo;
    private View view;

    public static Controlador getInsatnce() {
        return controlador == null ? controlador = new Controlador() : controlador;
    }

    public void crearJugador(String nombre, String avatar, Color color1, Color color2, Color color3, Color color4){
        modelo.crearJugador(nombre, avatar, color1, color2, color3, color4);
    }
    
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void setView(View view) {
        this.view = view;
    }

}
