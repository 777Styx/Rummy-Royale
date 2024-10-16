package registroMVC;

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

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void setView(View view) {
        this.view = view;
    }

}
