package serverCarlos;

import dtos.JuegoDTO;
import dtos.JugadorDTO;
import entidades.Juego;
import java.util.Observable;
import java.util.Observer;
import mensajes.Mensaje;
import mensajes.ResCrearPartida;
import mensajes.ResRegistroJugador;

/**
 *
 * @author julli
 */
public class Controlador implements Observer {

    private static Controlador instance;
    ClientHandler clientHandler;
    private static ExpertoJuego expertoJuego = new ExpertoJuego();
    private static ExpertoJugador expertoJugador = new ExpertoJugador();
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
        expertoJuego.crearJuego();
    }

    public void registrarJugador(ClientHandler aThis, JugadorDTO jugador) {
        this.clientHandler = aThis;
        if (expertoJuego.hasSpace()) {
            expertoJugador.registrarJugador(jugador);
        }
    }
    
    public void unirse(ClientHandler aThis) {
        this.clientHandler = aThis;
        //expertoJuego.unirse();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Mensaje) {

            Mensaje mensaje = (Mensaje) arg;

            switch (mensaje.getComando()) {
                case "PARTIDA_CREADA":
                    server.broadcastMessage(mensaje, clientHandler);
                    break;

                case "PARTIDA_NO_CREADA":
                    server.broadcastMessage(new ResCrearPartida("PARTIDA_NO_CREADA"), clientHandler);
                    break;
                case "JUGADOR_REGISTRADO":
                    JugadorDTO jugadorDTO = (JugadorDTO) arg;
                    server.broadcastMessage(new ResRegistroJugador(jugadorDTO), clientHandler);
                    break;
                case "PARTIDA_LLENA":
                    break;

                default:
                    System.out.println("Mensaje no reconocido (BBControlador): " + mensaje);
            }
        }

        if (arg instanceof JugadorDTO) {
            JugadorDTO jugadorDTO = (JugadorDTO) arg;
            server.broadcastMessage(new ResRegistroJugador(jugadorDTO), clientHandler);
        }
    }
}
