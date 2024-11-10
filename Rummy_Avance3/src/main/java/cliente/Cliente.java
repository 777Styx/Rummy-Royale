package cliente;

import dto.JuegoDTO;
import dto.JugadorDTO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author julli
 */
public class Cliente {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 2222);
            System.out.println("Server connected papu :v");

            //input outpu
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //entity to send
            JuegoDTO juegoDTO = new JuegoDTO();

            //Send dto paps
            out.writeObject(juegoDTO);
            System.out.println("se envio juas");

            String response = (String) in.readObject();
            System.out.println("RESPUESTA : " + response);

            socket.close();
        } catch (Exception ex) {

        }
    }

//    private Socket socket;
//    private ObjectOutputStream salida;
//    private ObjectInputStream entrada;
//
//    public Cliente(String host, int puerto) throws IOException {
//        socket = new Socket(host, puerto);
//        salida = new ObjectOutputStream(socket.getOutputStream());
//        entrada = new ObjectInputStream(socket.getInputStream());
//
//        if (entrada.readBoolean()) {
//            System.out.println("Conexión al servidor establecida.");
//        } else {
//            System.out.println("Servidor lleno. No se puede conectar.");
//            socket.close();
//        }
//    }
//
//    public void enviarAccion(String accion) throws IOException {
//        salida.writeObject(accion);
//        salida.flush();
//    }
//
//    public String recibirRespuesta() throws IOException, ClassNotFoundException {
//        return (String) entrada.readObject();
//    }
//
//    public void cerrar() throws IOException {
//        socket.close();
//    }
//
//    public static void main(String[] args) {
//        try {
//            Cliente cliente = new Cliente("localhost", 3000);
//            cliente.enviarAccion("UNIRSE");
//            System.out.println(cliente.recibirRespuesta());
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
