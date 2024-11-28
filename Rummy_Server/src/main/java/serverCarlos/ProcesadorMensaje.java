package serverCarlos;

import mensajes.Mensaje;
import mensajes.MessageManager;

/**
 *
 * @author carlo
 */
public class ProcesadorMensaje {

    private ClientHandler clientHandler;
    private ManejadorComando manejadorComando;

    public ProcesadorMensaje(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        this.manejadorComando = new ManejadorComando(clientHandler);
    }

    public void procesarMensaje(String inputLine) {
        try {
            Mensaje mensaje = MessageManager.fromJson(inputLine);
            System.out.println("client handler: se recibio la peticion " + mensaje.getComando());
            manejadorComando.manejarComando(mensaje);

        } catch (Exception e) {
            System.err.println("Error procesando mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
