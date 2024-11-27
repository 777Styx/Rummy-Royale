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
        expertoJuego.crearJuego(); // <- boolean 
        //server.broadcastMessage(mensaje, ch);
    }

    public void registrarJugador(ClientHandler aThis, JugadorDTO jugador) {
        this.clientHandler = aThis;
        if (expertoJuego.hasSpace()) {
            expertoJugador.registrarJugador(jugador);

        }

    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Mensaje) {
            Mensaje msj = (Mensaje) arg;
            String mensaje = msj.getComando();

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

                case "JUGADOR_REGISTRADO":
                    if (clientHandler != null) {
                        JugadorDTO jugadorDTO = (JugadorDTO) arg;
                        server.broadcastMessage(new ResRegistroJugador(jugadorDTO), clientHandler);
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

        if (arg instanceof JugadorDTO) {
            JugadorDTO jugadorDTO = (JugadorDTO) arg;
            server.broadcastMessage(new ResRegistroJugador(jugadorDTO), clientHandler);
        }
    }
}
