package menuMVC;

import dtos.JuegoDTO;
import dtos.JugadorDTO;
import java.awt.Color;
import partidaMVC.ControladorJuego;
import partidaMVC.ModeloJuego;
import partidaMVC.VistaJuego;

/**
 *
 * @author puerta
 */
public class ControladorMenu {

    private static ControladorMenu controladorMenu;
    private final ModeloMenu modeloMenu;

    public ControladorMenu(ModeloMenu modeloMenu) {
        this.modeloMenu = modeloMenu;

    }
    
    public void crearJugador(JugadorDTO jugador) {
        modeloMenu.registrarJugador(jugador);
    }

    public void crearPartida(int puerto) {
        modeloMenu.crearPartida(puerto);
    }

    public void configurarPartida(int comodines, int rango) {
        modeloMenu.configurarPartida(comodines, rango);
    }

    public void unirseAPartida(int puerto) {
        modeloMenu.unirseAPartida(puerto);
    }

    public void mostrarTablero() {
//        ModeloJuego modeloJuego = ModeloJuego.getInstance(cliente);
//        ControladorJuego controladorJuego = new ControladorJuego(modeloJuego);
//        VistaJuego vistaJuego = VistaJuego.getInstance(controladorJuego);
//        modeloJuego.addObserver(vistaJuego);
//        vistaJuego.setVisible(true);
        ControladorJuego controladorJuego = ControladorJuego.getInstance();
        controladorJuego.inicializar(this.modeloMenu.getCliente());
    }
}
