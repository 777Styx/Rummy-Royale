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
    
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Mensaje) {
            Mensaje mensaje = (Mensaje) arg;
            
            System.out.println("CONTROLADOR esta recibiendo esto: " + mensaje.getComando());
            
            server.broadcastMessage(mensaje, clientHandler);
            
            
            // me di cuenta muy tarde que este puto swithc no servia pa una puta mierda
            
//            switch (mensaje.getComando()) {
//                case "PARTIDA_CREADA":
//                    server.broadcastMessage(mensaje, clientHandler);
//                    break;
//                case "PARTIDA_NO_CREADA":
//                    server.broadcastMessage(new ResCrearPartida("PARTIDA_NO_CREADA"), clientHandler);
//                    break;
//                case "JUGADOR_REGISTRADO":
//                    server.broadcastMessage(mensaje, clientHandler);
//                    break;
//                case "PARTIDA_CONFIGURADA":
//                    server.broadcastMessage(mensaje, clientHandler);
//                    break;
//                case "JUGADOR_UNIDO":
//                    server.broadcastMessage(mensaje, clientHandler);
//                default:
//                    System.out.println("Mensaje no reconocido (BBControlador): " + mensaje);
//            }
        }
    }
}
