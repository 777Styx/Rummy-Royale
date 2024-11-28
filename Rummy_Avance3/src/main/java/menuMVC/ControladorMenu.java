package menuMVC;

import clienteCarlitos.Cliente;
import dtos.JuegoDTO;
import java.awt.Color;
import java.util.Observable;

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

    public void crearJugador(String nombre, String avatar, Color color1, Color color2, Color color3, Color color4) {
        modeloMenu.registrarJugador(nombre, avatar, color1, color2, color3, color4);

    }

    public void crearPartida(int puerto) {
        modeloMenu.crearPartida(puerto);
    }
    
    public void configurarPartida(int comodines,int rango) {
        modeloMenu.configurarPartida(comodines, rango);
    }
    
    public void unirseAPartida(int puerto) {
        modeloMenu.unirseAPartida(puerto);
    }
}
