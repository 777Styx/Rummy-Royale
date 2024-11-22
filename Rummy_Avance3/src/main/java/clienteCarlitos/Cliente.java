package clienteCarlitos;

import com.google.gson.Gson;
import common.Command;
import common.NetworkMessage;
import dtos.JugadorDTO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import menuMVC.ModeloMenu;

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
    private ModeloMenu modeloMenu;

    private MessageListener messageListener;

    public Cliente(ModeloMenu modeloMenu) {
        this.modeloMenu = modeloMenu;
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

    // enviar mensajes al servidor
    private void sendMessage(String command, Object data) {
        if (connected && out != null) {
            String message = serializableCommanWithData(command, data);
            out.println(message);
        }
    }

    // metodos para interactuar con el juego
    public void crearPartida() {
        sendMessage(Command.CREAR_PARTIDA, null);
    }

    public void moverFicha() {
        sendMessage(Command.MOVER_FICHA, null);
    }

    public void registrarJugador(JugadorDTO jugador) {
        sendMessage(Command.REGISTRAR_JUGADOR, jugador);
    }

    private String serializableCommanWithData(String command, Object data) {
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("command", command);
        messageMap.put("data", data);

        return new Gson().toJson(messageMap);
    }

    private class MessageListener implements Runnable {

        private boolean running = true;

        @Override
        public void run() {
            try {
                String message;
                while (running && (message = in.readLine()) != null) {
                    System.out.println("Mensaje del servidor: " + message);
                    // aki se notifica al modelo del mvc
                    if (modeloMenu != null) {
                        modeloMenu.updateEstadoJuego(message);
                    }
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
