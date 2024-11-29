package serverCarlos;

import com.google.gson.Gson;
import common.Command;
import dtos.JuegoDTO;
import dtos.JugadorDTO;
import mensajes.Mensaje;
import mensajes.ReqConfigurarPartida;
import mensajes.ReqRegistroJugador;
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
                //handleUnirse((ReqUnirse) mensaje);
                break;
            case "CONFIGURAR_PARTIDA":
                handleConfigurarPartida((ReqConfigurarPartida) mensaje);
                break;
            default:

        }
    }

    // Esto esta en ManejadorComando
    private void handleCrearPartida(Mensaje mensaje) {
        controlador.crearPartida(mensaje, clientHandler);
    }

    private void handleRegistrarJugador(ReqRegistroJugador mensaje) {
        JugadorDTO jugador = mensaje.getJugador();
        //controlador.registrarJugador(clientHandler, jugador);
    }
    
    private void handleUnirse() {
        //controlador.unirse(clientHandler);
    }

    private void handleConfigurarPartida(ReqConfigurarPartida reqConfigurarPartida) {
        controlador.configurarPartida(clientHandler, reqConfigurarPartida);
    }
}
