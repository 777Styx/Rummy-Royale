package origin;

import partidaMVC.Controlador;
import menuMVC.ControladorMenu;
import menuMVC.ModeloMenu;
import menuMVC.VistaUnirse;

/**
 *
 * @author julli
 */
public class Main {

    public static void main(String[] args) {

        ModeloMenu modeloUnirse = new ModeloMenu();

        //ControladorUnirse controladorUnirse = ControladorMenu.getInstancia();
        ControladorMenu controladorUnirse = new ControladorMenu(modeloUnirse);
        VistaUnirse vistaUnirse = new VistaUnirse(controladorUnirse);
        modeloUnirse.addObserver(vistaUnirse);
        
        
        

        java.awt.EventQueue.invokeLater(() -> {
            vistaUnirse.setVisible(true);
        });
    }
}
