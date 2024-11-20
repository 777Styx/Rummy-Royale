package menuMVC;

import clienteCarlitos.Cliente;
import dto.JuegoDTO;
import java.awt.Color;
import java.util.Observable;

/**
 *
 * @author puerta
 */
public class ControladorMenu {

    private static ControladorMenu controladorMenu;
    private final ModeloMenu modeloMenu;
    private Cliente cliente;

    public ControladorMenu(ModeloMenu modeloMenu) {
        // Inicializar si es necesario      
        this.modeloMenu = modeloMenu;

    }

//
//    // Método estático para obtener la instancia única del controlador
//    public static ControladorMenu getInstancia() {
//        if (instancia == null) {
//            instancia = new ControladorMenu(ModeloMenu modelo);
//        }
//        return instancia;
//    }
    // Método para gestionar la acción de unirse a la partida
    public void unirseAPartida(String nombreJugador) {
        // Aquí se puede colocar la lógica para unirse a una partida.
        // Podría interactuar con el modelo y actualizar la vista.
        System.out.println(nombreJugador + " se ha unido a la partida.");
    }

    // Método para gestionar la acción de unirse a la partida
    public void unirseAPartidaView(String nombreJugador) {
        // Lógica para unirse a una partida
        System.out.println(nombreJugador + " se ha unido a la partida.");

        // Aquí es donde controlamos la lógica para interactuar con el ControladorRegistro y la vista
        // Obtener el controlador de registro (singleton)
        //  ControladorRegistro controladorRegistro = ControladorRegistro.getInsatnce();
//
//        // Crear la vista de registro y asociarla con el controlador
//        ViewRegistro vistaRegistro = new ViewRegistro(controladorRegistro);
//
//        // Asignar la vista al controlador de registro
//        controladorRegistro.setView(vistaRegistro);
//
//        // Mostrar la vista asociada
//        controladorRegistro.mostrarVista();
    }

    public void crearJugador(String nombre, String avatar, Color color1, Color color2, Color color3, Color color4) {
        //modeloMenu.registrarJugador(nombre, avatar, color1, color2, color3, color4);

        //Prueba con server ajua
        // prueba
        //modeloMenu.imprimirJugadores();
    }

//    public void crearPartida(JuegoDTO juegoDTO) {
//        cliente.sendMessageObject(juegoDTO);
//        
//        
//    }
    public void crearPartida() {
        modeloMenu.crearPartida();
    }

    // se supone que esto no debe de usarse
    public void mostrarRegistro() {
       // modeloMenu.mostrarRegistro(true);
    }

    // carlos version
    public void conectarAPartida(String direccion, int puerto) {
        System.out.println("Controlador: creando conexion");
        modeloMenu.crearConexion();
    }
}
