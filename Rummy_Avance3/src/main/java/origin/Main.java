package origin;

import partidaMVC.ControladorJuego;
import menuMVC.ControladorMenu;
import menuMVC.ModeloMenu;
import menuMVC.VistaConfiguracion;
import menuMVC.VistaRegistro;
import menuMVC.VistaUnirseCrear;

/**
 *
 * @author julli
 */
public class Main {

    public static void main(String[] args) {

        ModeloMenu modeloMenu = new ModeloMenu();
        ControladorMenu controladorMenu = new ControladorMenu(modeloMenu);
        //Crear vistas y agregar controladores
        VistaUnirseCrear vistaUnirseCrear = new VistaUnirseCrear(controladorMenu);
        VistaConfiguracion vistaConfiguracion = new VistaConfiguracion(controladorMenu);
        VistaRegistro vistaRegistro = new VistaRegistro(controladorMenu);

        //Se agregan los observadores
        modeloMenu.addObserver(vistaUnirseCrear);
        modeloMenu.addObserver(vistaConfiguracion);
        modeloMenu.addObserver(vistaRegistro);
        

        //Mostramos la primera vista
        java.awt.EventQueue.invokeLater(() -> {
            vistaUnirseCrear.setVisible(true);
        });
    }
}
