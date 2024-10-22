package origin;

import tableMVC.Controlador;
import unsirseCrearMVC.ControladorUnirse;
import unsirseCrearMVC.ModeloUnirse;
import unsirseCrearMVC.VistaUnirse;

/**
 *
 * @author julli
 */
public class Main {

    public static void main(String[] args) {

        ModeloUnirse modeloUnirse = new ModeloUnirse();

        //ControladorUnirse controladorUnirse = ControladorUnirse.getInstancia();
        ControladorUnirse controladorUnirse = new ControladorUnirse(modeloUnirse);

        VistaUnirse vistaUnirse = new VistaUnirse(controladorUnirse);

        modeloUnirse.addObserver(vistaUnirse);

        java.awt.EventQueue.invokeLater(() -> {
            vistaUnirse.setVisible(true);
        });
    }
}
