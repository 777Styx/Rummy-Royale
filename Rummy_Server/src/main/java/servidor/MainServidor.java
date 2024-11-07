package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julli
 */
public class MainServidor {

    private static List<Entidad> entidades = new ArrayList<>();

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("Running");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected");

            //in and out
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            
            
        } catch (IOException ex) {

        }

//        int puerto = 3000; // El puerto en el que quieres que escuche el servidor
//        try {
//            Servidor servidor = new Servidor(puerto);
//            servidor.iniciar(); // Inicia el servidor
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
