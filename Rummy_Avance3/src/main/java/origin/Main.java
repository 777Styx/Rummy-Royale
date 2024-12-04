package origin;

import Cliente.Cliente;
import javax.swing.SwingUtilities;
import partidaMVC.ControladorJuego;
import menuMVC.ControladorMenu;
import menuMVC.ModeloMenu;
import menuMVC.VistaConfig;
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
        
        Cliente cliente = new Cliente();
        
        // Modelo y Controlador de menuMVC
        ModeloMenu modeloMenu = ModeloMenu.getInstance(cliente);
        ControladorMenu controladorMenu = new ControladorMenu(modeloMenu);

        // Modelo y Controlador de JuegoMVC
        //ModeloJuego modeloJuego = ModeloJuego.getInstance();
        //ControladorJuego controladorJuego = new ControladorJuego(modeloJuego);

        //Crear vistas y agregar controladores
        VistaUnirseCrear vistaUnirseCrear = new VistaUnirseCrear(controladorMenu);
        VistaConfig vistaConfiguracion = new VistaConfig(controladorMenu);
        VistaRegistro vistaRegistro = new VistaRegistro(controladorMenu);

        //VistaJuego vistaJuego = new VistaJuego(controladorJuego);
        

        //Se agregan los observadores
        modeloMenu.addObserver(vistaUnirseCrear);
        modeloMenu.addObserver(vistaConfiguracion);
        modeloMenu.addObserver(vistaRegistro);

        //modeloJuego.addObserver(vistaJuego);
        
        //Mostramos la primera vista
        java.awt.EventQueue.invokeLater(() -> {
            vistaUnirseCrear.setVisible(true);
        });
        
        
        // random comment here!
    }
}
