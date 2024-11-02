package menuMVC;

import java.awt.Color;

/**
 *
 * @author puerta
 */
public class ControladorMenu {

    // Variable estática que contiene la única instancia del controlador
    private static ControladorMenu controladorMenu;
    private final ModeloMenu modeloMenu;

    // Constructor privado para evitar la creación de nuevas instancias
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
        //  modeloMenu.crearJugador(nombre, avatar, color1, color2, color3, color4);
    }

//    public void setModelo(ModeloMenu modeloMenu) {
//        this.modeloMenu = modeloMenu;
//    }
//    public void setView(ViewRegistro view) {
//        this.view = view;
//    }
    
    
    
//    public void crearJuego() {
//
//        modeloMenu.crearJuego();
//    }
    
    public void mostrarRegistro() {
        modeloMenu.mostrarRegistro(true);
    }
}
