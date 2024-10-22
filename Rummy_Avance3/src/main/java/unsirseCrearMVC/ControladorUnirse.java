package unsirseCrearMVC;

import registroMVC.ControladorRegistro;
import registroMVC.ViewRegistro;

/**
 *
 * @author puerta
 */
public class ControladorUnirse {

    // Variable estática que contiene la única instancia del controlador
    private static ControladorUnirse instancia;
    private final ModeloUnirse modelo;

    // Constructor privado para evitar la creación de nuevas instancias
    public ControladorUnirse(ModeloUnirse modelo) {
        // Inicializar si es necesario
        this.modelo = modelo;

    }

//
//    // Método estático para obtener la instancia única del controlador
//    public static ControladorUnirse getInstancia() {
//        if (instancia == null) {
//            instancia = new ControladorUnirse(ModeloUnirse modelo);
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

    public void crearJuego() {
        
        modelo.crearJuego();
    }
}
