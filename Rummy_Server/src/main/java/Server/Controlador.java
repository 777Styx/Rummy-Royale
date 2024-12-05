package Server;

import expertos.ExpertoUnirse;
import expertos.ExpertoSolicitarInicio;
import expertos.ExpertoResponderSolicitudInicio;
import expertos.ExpertoCrearMazo;
import expertos.ExpertoCrearFichasNumericas;
import expertos.Experto;
import expertos.ExpertoCrearComodines;
import expertos.ExpertoAsignarTurnos;
import expertos.ExpertoCrearPartida;
import expertos.ExpertoRepartirFichas;
import expertos.ExpertoRegistrar;
import expertos.ExpertoEmpezarPartida;
import entidades.Juego;
import expertos.ExpertoPasarTurno;
import expertos.ExpertoTomarFicha;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import mensajes.Mensaje;
import mensajes.ReqIniciarPartida;

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
    // blackboard vFinal
    private Queue<String> accionesPendientes = new LinkedList<>();

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
        this.expertos.put("repartirFichas", new ExpertoRepartirFichas());
        this.expertos.put("asignarTurnos", new ExpertoAsignarTurnos());
        this.expertos.put("empezarPartida", new ExpertoEmpezarPartida());
        this.expertos.put("pasarTurno", new ExpertoPasarTurno());
        this.expertos.put("tomarFicha", new ExpertoTomarFicha());
    }

    public void realizarAccion(String accion, Mensaje mensaje) {
        Experto experto = expertos.get(accion);
        if (experto != null) {
            experto.ejecutar(juego, mensaje);
        } else {
            throw new IllegalArgumentException("Acción desconocida: " + accion);
        }
    }

    //bb v2
    private void ejecutarSiguienteAccion(Mensaje mensaje) {
        if (!accionesPendientes.isEmpty()) {
            String accion = accionesPendientes.poll();
            realizarAccion(accion, mensaje);
        } else {
            System.out.println("Todas las acciones han sido completadas.");
        }
    }

    public void crearPartida(Mensaje mensaje, ClientHandler aThis) {
        this.clientHandler = aThis;
        realizarAccion("crearPartida", mensaje);
    }

    public void triggerConfiguracionPartida(ClientHandler aThis, Mensaje mensaje) {
        this.clientHandler = aThis;
        accionesPendientes.add("crearFichasNumericas");
        accionesPendientes.add("crearComodines");
        accionesPendientes.add("crearMazo");

        ejecutarSiguienteAccion(mensaje);
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

    public void triggerIniciarPartida(Mensaje mensaje) {
        accionesPendientes.add("repartirFichas");
        accionesPendientes.add("asignarTurnos");
        accionesPendientes.add("empezarPartida");
        
        ejecutarSiguienteAccion(mensaje);
    }

    public void pasarTurno(Mensaje mensaje, ClientHandler aThis) {
        realizarAccion("pasarTurno", mensaje);
    }
    
    public void tomarFicha(Mensaje mensaje, ClientHandler aThis){
        this.clientHandler = aThis;
        realizarAccion("tomarFicha", mensaje);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Mensaje) {
            Mensaje mensaje = (Mensaje) arg;
            if (mensaje.getComando().equals("CONFIGURAR_PARTIDA")) {
                if (!juego.estaConfigurado()) {
                    ejecutarSiguienteAccion(mensaje);
                } else {
                    System.out.println("El juego ya esta configurado");
                }
            } 
            
            
            if(mensaje.getComando().equals("INICIAR_PARTIDA")) {
                if(!juego.partidaEstaEmpezada()) {
                    ejecutarSiguienteAccion(mensaje);
                } else {
                    System.out.println("Juego ya esta empezado!!!!!");
                }
            }
            
            if(mensaje.getComando().equals("TODOS_ACEPTARON")) {
                triggerIniciarPartida(new ReqIniciarPartida());
            }
            
            
            else {
                System.out.println("Controlador esta recibiendo esto: " + mensaje.getComando());
                server.broadcastMessage(mensaje, clientHandler);
            }

        }
    }
}
