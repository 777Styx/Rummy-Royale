package origin;

import partidaMVC.Controlador;
import menuMVC.ControladorMenu;
import menuMVC.ModeloMenu;
import menuMVC.VistaRegistro;
import menuMVC.VistaUnirse;

/**
 *
 * @author julli
 */
public class Main {

    public static void main(String[] args) {

        ModeloMenu modeloMenu = new ModeloMenu();
        ControladorMenu controladorMenu = new ControladorMenu(modeloMenu);
        VistaUnirse vistaUnirse = new VistaUnirse(controladorMenu); 
        modeloMenu.addObserver(vistaUnirse); // Agregar vistaUnirse como observador de modeloMenu

        java.awt.EventQueue.invokeLater(() -> {
            vistaUnirse.setVisible(true);
        });
    }
}
