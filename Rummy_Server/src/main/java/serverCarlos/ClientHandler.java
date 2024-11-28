package serverCarlos;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import common.Command;
import dtos.JugadorDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import mensajes.Mensaje;
import mensajes.MessageManager;

/**
 *
 * @author carlo
 */
public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Server server;
    private boolean running;
    private ProcesadorMensaje procesadorMensaje;

    public ClientHandler(Socket socket, Server server) {
        this.clientSocket = socket;
        this.server = server;
        this.running = true;
        this.procesadorMensaje = new ProcesadorMensaje(this);
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (running && !clientSocket.isClosed()) {
                String inputLine = in.readLine();
                if (inputLine == null) {
                    break;
                }
                System.out.println(inputLine);
                procesadorMensaje.procesarMensaje(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Error: Cliente desconectado");
        } finally {
            disconnect();
        }
    }

    public void sendMessage(Mensaje mensaje) {

        String jsonMessage = MessageManager.toJson(mensaje);

        if (out != null && !clientSocket.isClosed()) {
            out.println(jsonMessage);
        }
    }

    private void disconnect() {
        running = false;
        server.removeClient(this);
        try {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server getServer() {
        return server;
    }

}
