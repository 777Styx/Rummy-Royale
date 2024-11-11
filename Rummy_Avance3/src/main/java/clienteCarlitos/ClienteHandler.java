package clienteCarlitos;

import dto.JuegoDTO;
import entidades.Juego;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author julli
 */
public class ClienteHandler {

    public void procesarCrearPartida(Socket socket, Object objetoDTO) {

        try {
            if (objetoDTO instanceof JuegoDTO) {
                Juego juego = new Juego();
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(objetoDTO);
            }
        } catch (Exception ex) {

        }
    }
}
