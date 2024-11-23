package clienteCarlitos;

import com.google.gson.Gson;
import common.Command;
import dtos.JugadorDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
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
    private ModeloMenu modeloMenu;
    private String direccion;
    private String puerto;

    private MessageListener messageListener;

    public Cliente(ModeloMenu modeloMenu) {
        this.modeloMenu = modeloMenu;
        connected = false;
    }

    public boolean isConnected() {
        return connected;
    }

    public void connectToServer(int puerto) {
        try {
            socket = new Socket("localhost", puerto);
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
                    if (modeloMenu != null) {
                        System.out.println("Soy cliente y recibi esto: " + message);
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
