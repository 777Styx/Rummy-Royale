package Server;

import com.google.gson.Gson;
import dtos.JuegoDTO;
import dtos.JugadorDTO;
import mensajes.Mensaje;
import mensajes.ReqConfigurarPartida;
import mensajes.ReqPasarTurno;
import mensajes.ReqRegistroJugador;
import mensajes.ReqResponderSolicitudInicio;
import mensajes.ReqSolicitarInicio;
import mensajes.ReqUnirse;

/**
 *
 * @author carlo
 */
public class ManejadorComando {

    private ClientHandler clientHandler;
    private Controlador controlador;

    public ManejadorComando(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        this.controlador = Controlador.getInstance();
    }

    public void manejarComando(Mensaje mensaje) {
        if (mensaje == null) {
            System.out.println("mensaje nulo");
            return;
        }

        switch (mensaje.getComando()) {
            case "CREAR_PARTIDA":
                handleCrearPartida(mensaje);
                break;
            case "REGISTRAR_JUGADOR":
                handleRegistrarJugador((ReqRegistroJugador) mensaje);
                break;
            case "UNIRSE":
                handleUnirse((ReqUnirse) mensaje);
                break;
            case "CONFIGURAR_PARTIDA":
                handleConfigurarPartida((ReqConfigurarPartida) mensaje);
                break;
            case "SOLICITAR_INICIO":
                handleSolicitarInicio((ReqSolicitarInicio) mensaje);
                break;
            case "RESPONDER_SOLICITUD_INICIO":
                handleResponderSolicitudInicio((ReqResponderSolicitudInicio) mensaje);
                break;
            case "PASAR_TURNO":
                handlePasarTurno((ReqPasarTurno) mensaje);
                break;
            default:

        }
    }
    
    public void handlePasarTurno(Mensaje mensaje) {
        controlador.pasarTurno(mensaje, clientHandler);
    }
    
    private void handleResponderSolicitudInicio(Mensaje mensaje) {
        controlador.responderSolicitudInicio(mensaje, clientHandler);
    }
    
    private void handleSolicitarInicio(Mensaje mensaje) {
        controlador.solicitarInicio(mensaje, clientHandler);
    }
    
    private void handleCrearPartida(Mensaje mensaje) {
        controlador.crearPartida(mensaje, clientHandler);
    }

    private void handleConfigurarPartida(ReqConfigurarPartida reqConfigurarPartida) {
        controlador.triggerConfiguracionPartida(clientHandler, reqConfigurarPartida);
    }
    
    private void handleRegistrarJugador(ReqRegistroJugador mensaje) {
        controlador.registrarJugador(mensaje, clientHandler);
    }
    
    private void handleUnirse(ReqUnirse mensaje) {
        controlador.unirse(mensaje, clientHandler);
    }

    
}
