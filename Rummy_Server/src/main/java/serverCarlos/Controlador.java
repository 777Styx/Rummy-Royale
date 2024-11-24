package serverCarlos;

import dtos.JugadorDTO;
import entidades.Juego;
import java.util.Observable;
import java.util.Observer;
import mensajes.ResCrearPartida;

/**
 *
 * @author julli
 */
public class Controlador implements Observer {

    private static Controlador instance;
    ClientHandler clientHandler;
    private static ExpertoJuego expertJuego = new ExpertoJuego();
    private static ExpertoJugador expertJugador = new ExpertoJugador();
    Server server = Server.getInstance();
    
    public enum EstadoJuego {
        ESPERANDO_JUGADORES,
        CONFIGURANDO,
        REGISTRO,
        LISTO,
        EN_PROGRESO
    }
    
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
        expertJuego.crearJuego(); // <- boolean 
        //server.broadcastMessage(mensaje, ch);
    }
    
    public void registrarJugador(ClientHandler aThis, JugadorDTO jugador) {
        this.clientHandler = aThis;
        expertJugador.registrarJugador(jugador);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            String mensaje = (String) arg;

             switch (mensaje) {
            case "CREADO":
                if (clientHandler != null) {
                    server.broadcastMessage(new ResCrearPartida("PARTIDA_CREADA"), clientHandler);
                }
                break;

            case "YA_CREADO":
                if (clientHandler != null) {
                    server.broadcastMessage(new ResCrearPartida("PARTIDA_NO_CREADA"), clientHandler);
                }
                break;

            case "JUGADOR_CREADO":
                if (clientHandler != null) {
                    
                }
                break;

            case "PARTIDA_LLENA":
                if (clientHandler != null) {
                    
                }
                break;

            default:
                System.out.println("Mensaje no reconocido (BBControlador): " + mensaje);
        }
        }
    }
}
