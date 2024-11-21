package serverCarlos;

import entidades.Juego;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author julli
 */
public class Controlador implements Observer {

    private static Controlador instance;
    private EstadoJuego estadoActual;
    ClientHandler clientHandler;
    
    
    public enum EstadoJuego {
        ESPERANDO_JUGADORES,
        CONFIGURANDO,
        REGISTRO,
        LISTO,
        EN_PROGRESO
    }

    private static ExpertoJuego expertJuego = new ExpertoJuego();

    private Controlador() {

    }

    public static synchronized Controlador getInstance() {
        if (instance == null) {
            instance = new Controlador();
        }
        return instance;
    }
    
    public void crearJuego(ClientHandler ch) {
        this.clientHandler = ch;
        System.out.println("Creando juego en controlador BB");
        expertJuego.crearJuego();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("El controlador fue notificado: " + arg);
        if (arg instanceof String) {
            String mensaje = (String) arg;

            if (mensaje.equals("CREADO")) {
                if(clientHandler != null) {
                    clientHandler.sendMessage("CREADO");
                }
            } else if (mensaje.equals("YA_CREADO")) {
                if(clientHandler != null) {
                    clientHandler.sendMessage("YA_CREADO");
                }
                
            }
        }
    }
}
