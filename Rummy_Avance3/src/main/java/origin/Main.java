package origin;

import partidaMVC.ControladorJuego;
import menuMVC.ControladorMenu;
import menuMVC.ModeloMenu;
import menuMVC.VistaConfiguracion;
import menuMVC.VistaRegistro;
import menuMVC.VistaUnirseCrear;
import partidaMVC.ModeloJuego;
import partidaMVC.VistaJuego;

/**
 *
 * @author julli
 */
public class Main {

    public static void main(String[] args) {

        // Modelo y Controlador de menuMVC
        ModeloMenu modeloMenu = new ModeloMenu();
        ControladorMenu controladorMenu = new ControladorMenu(modeloMenu);
        
        // Modelo y Controlador de partidaMVC
        ModeloJuego modeloJuego = new ModeloJuego();
        ControladorJuego controladorJuego = new ControladorJuego(modeloJuego);
        
        //Crear vistas y agregar controladores
        VistaUnirseCrear vistaUnirseCrear = new VistaUnirseCrear(controladorMenu);
        VistaConfiguracion vistaConfiguracion = new VistaConfiguracion(controladorMenu);
        VistaRegistro vistaRegistro = new VistaRegistro(controladorMenu);
        
        VistaJuego vistaJuego = new VistaJuego(controladorJuego);

        //Se agregan los observadores
        modeloMenu.addObserver(vistaUnirseCrear);
        modeloMenu.addObserver(vistaConfiguracion);
        modeloMenu.addObserver(vistaRegistro);
        
        modeloJuego.addObserver(vistaJuego);

        //Mostramos la primera vista
        java.awt.EventQueue.invokeLater(() -> {
            vistaUnirseCrear.setVisible(true);
        });
    }
}
