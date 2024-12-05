package Server;

import dtos.JuegoDTO;
import dtos.JugadorDTO;
import entidades.IFicha;
import entidades.Juego;
import entidades.Mazo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    Server server = Server.getInstance();
    private Map<String, Experto> expertos;
    Juego juego = Juego.getInstance();

    private Controlador() {
        this.expertos = new HashMap<>();
        this.expertos.put("crearPartida", new ExpertoCrearPartida());
        this.expertos.put("crearFichasNumericas", new ExpertoCrearFichasNumericas());
        this.expertos.put("crearComodines", new ExpertoCrearComodines());
        this.expertos.put("crearMazo", new ExpertoCrearMazo());
        this.expertos.put("registrarJugador", new ExpertoRegistrar());
        this.expertos.put("unirse", new ExpertoUnirse());
        this.expertos.put("solicitarInicio", new ExpertoSolicitarInicio());
        this.expertos.put("responderSolicitudInicio", new ExpertoResponderSolicitudInicio());
    }

    public void realizarAccion(String accion, Mensaje mensaje) {
        Experto experto = expertos.get(accion);
        if (experto != null) {
            experto.ejecutar(juego, mensaje);
        } else {
            throw new IllegalArgumentException("Acción desconocida: " + accion);
        }
    }

    public void crearPartida(Mensaje mensaje, ClientHandler aThis) {
        this.clientHandler = aThis;
        realizarAccion("crearPartida", mensaje);
    }

    public void configurarPartida(ClientHandler aThis, Mensaje mensaje) {
        this.clientHandler = aThis;
        realizarAccion("crearFichasNumericas", mensaje);
        realizarAccion("crearComodines", mensaje);
        realizarAccion("crearMazo", mensaje);
    }

    public static synchronized Controlador getInstance() {
        if (instance == null) {
            instance = new Controlador();
        }
        return instance;
    }

    public void registrarJugador(Mensaje mensaje, ClientHandler aThis) {
        this.clientHandler = aThis;
        realizarAccion("registrarJugador", mensaje);
    }

    public void unirse(Mensaje mensaje, ClientHandler aThis) {
        this.clientHandler = aThis;
        realizarAccion("unirse", mensaje);
    }

    public void solicitarInicio(Mensaje mensaje, ClientHandler aThis) {
        this.clientHandler = aThis;
        realizarAccion("solicitarInicio", mensaje);
    }

    public void responderSolicitudInicio(Mensaje mensaje, ClientHandler aThis) {
        this.clientHandler = aThis;
        realizarAccion("responderSolicitudInicio", mensaje);
    }

    public void iniciarPartida() {
        realizarAccion("repartirFichas", null);
        realizarAccion("asignarTurnos", null);
        realizarAccion("empezarPartida", null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Mensaje) {
            Mensaje mensaje = (Mensaje) arg;
            System.out.println("Controlador esta recibiendo esto: " + mensaje.getComando());
            server.broadcastMessage(mensaje, clientHandler);
        } else if(arg instanceof String) {
            String mensaje = (String) arg;
            if(mensaje.equals("INICIANDO_JUEGO")) {
                iniciarPartida();
            }
        }
    }
}
