package clienteCarlitos;

import common.Command;
import common.NetworkMessage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class Cliente {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean connected;
    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    private MessageListener messageListener;

    public Cliente() {
        connected = false;
    }

    public boolean isConnected() {
        return connected;
    }

    public void connectToServer() {
        try {
            socket = new Socket(HOST, PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            connected = true;
            System.out.println("Conectado al servidor");

            // Iniciar thread para escuchar mensajes del servidor
            messageListener = new MessageListener();
            new Thread(messageListener).start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo conectar al servidor");
        }
    }

    // Métodos para interactuar con el juego
    public void crearPartida() {
        sendMessage(Command.CREAR_PARTIDA);
    }

    public void moverFicha() {
        sendMessage(Command.MOVER_FICHA);
    }

    private void sendMessage(String message) {
        if (connected && out != null) {
            out.println(message);
        }
    }

    // Clase interna para escuchar mensajes del servidor
    private class MessageListener implements Runnable {

        private boolean running = true;

        @Override
        public void run() {
            try {
                String message;
                while (running && (message = in.readLine()) != null) {
                    // Procesar mensaje recibido del servidor
                    System.out.println("Mensaje del servidor: " + message);
                    // Aquí puedes agregar la lógica para actualizar la UI o el estado del juego
                }
            } catch (IOException e) {
                System.out.println("Desconectado del servidor");
                connected = false;
            }
        }

        public void stop() {
            running = false;
        }

    }
}
