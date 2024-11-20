package serverCarlos;

import common.Command;
import common.NetworkMessage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public ClientHandler(Socket socket, Server server) {
        this.clientSocket = socket;
        this.server = server;
        this.running = true;
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
                    break; // Cliente se desconectó
                }
                processMessage(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Cliente desconectado");
        } finally {
            disconnect();
        }
    }

    private void processMessage(String message) {
        // Aquí procesamos los mensajes según el protocolo
        switch (message) {
            case Command.CREAR_PARTIDA:
                handleCrearPartida();
                break;
            case Command.MOVER_FICHA:
                handleMoverFicha();
                break;
            // Agrega más casos según necesites
        }
    }

    private void handleCrearPartida() {
        // Implementa la lógica para crear partida
        System.out.println("Creando partida...");
        sendMessage("Partida creada exitosamente");
    }

    private void handleMoverFicha() {
        System.out.println("Moviendo ficha...");
        sendMessage("Ficha movida exitosamente");
    }

    public void sendMessage(String message) {
        if (out != null && !clientSocket.isClosed()) {
            out.println(message);
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
}
