package clienteCarlitos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.Command;
import dtos.JuegoDTO;
import dtos.JugadorDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import mensajes.Mensaje;
import mensajes.MessageManager;
import mensajes.MsgConfigurarPartida;
import mensajes.MsgCrearPartida;
import mensajes.MsgRegistroJugador;
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
            messageListener = new MessageListener();
            new Thread(messageListener).start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo conectar al servidor");
        }
    }

    private void sendMessage(Mensaje mensaje) {
        if (connected && out != null) {
            //String message = MessageSerializer.serializableCommanWithData(mensaje);
            String jsonMessage = MessageManager.toJson(mensaje);
            out.println(jsonMessage);
        }
    }
    
    public void crearPartida() {
        sendMessage(new MsgCrearPartida());
    }

    public void registrarJugador(JugadorDTO jugador) {
        sendMessage(new MsgRegistroJugador(jugador));
    }

    public void configurarPartida(JuegoDTO configuracion) {
        sendMessage(new MsgConfigurarPartida(configuracion));
    }

    private class MessageListener implements Runnable {
        private boolean running = true;

        @Override
        public void run() {
            try {
                String message;
                while (running && (message = in.readLine()) != null) {
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
