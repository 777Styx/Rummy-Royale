package serverCarlos;

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
    }

    public void realizarAccion(String accion) {
        Experto experto = expertos.get(accion);
        if (experto != null) {
            experto.ejecutar(juego);
        } else {
            throw new IllegalArgumentException("Acción desconocida: " + accion);
        }
    }
    
    public void crearPartida(Mensaje mensaje, ClientHandler aThis) {
        this.clientHandler = aThis;
        realizarAccion("crearPartida");
        
    }
    
    
    
    public static synchronized Controlador getInstance() {
        if (instance == null) {
            instance = new Controlador();
        }
        return instance;
    }
    
    
//    public void registrarJugador(ClientHandler aThis, JugadorDTO jugador) {
//        this.clientHandler = aThis;
//        if (expertoJuego.hasSpace()) {
//            expertoJugador.registrarJugador(jugador);
//        } else {
//            System.out.println("NO ZE REGISTRARA NADA PORKE NO AI ESPASIO");
//        }
//    }

//    public void unirse(ClientHandler aThis) {
//        this.clientHandler = aThis;
//        //expertoJuego.unirse();
//    }

//    public void configurarPartida(ClientHandler aThis, JuegoDTO juego) {
//        System.out.println("cONTOrOLdOR cONfIguRANdO");
//        this.clientHandler = aThis;        
//        Map<String, List<IFicha>> fichas = expertoFicha.crearFichas(juego);
//        List<IFicha> comodines = fichas.get("comodines");
//        List<IFicha> fichasNumericas = fichas.get("fichasNumericas");
//        Mazo mazo = expertoMazo.crearMazo(fichasNumericas, comodines);
//        expertoJuego.configurarPartida(mazo);
//        
//    }

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
                    System.out.println("SEgun, si se registro en Juego");
                    server.broadcastMessage(new ResRegistroJugador(jugadorDTO), clientHandler);
                    break;
                case "PARTIDA_LLENA":
                    break;
                case "PARTIDA_CONFIGURADA":
                    server.broadcastMessage(mensaje, clientHandler);
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
