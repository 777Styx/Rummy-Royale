package serverCarlos;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import common.Command;
import common.NetworkMessage;
import dto.JugadorDTO;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class ClientHandler implements Runnable {
    
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Server server;
    private boolean running;
    private Controlador controlador;

    public ClientHandler(Socket socket, Server server) {
        this.clientSocket = socket;
        this.server = server;
        this.running = true;
        clientHandlers.add(this);
        this.controlador = Controlador.getInstance();
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
                processMessage(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Error: Cliente desconectado");
        } finally {
            disconnect();
        }
    }
//
//    private void processMessage(String message) {
//        switch (message) {
//            case Command.CREAR_PARTIDA:
//                handleCrearPartida();
//                break;
//            case Command.MOVER_FICHA:
//                handleMoverFicha();
//                break;
//                // mas acciones
//        }
//    }
    
    private void processMessage(String message) {
    try {
        // deserializar el mensaje
        Gson gson = new Gson();
        Map<String, Object> messageMap = gson.fromJson(message, Map.class);

        String command = (String) messageMap.get("command");
        Object data = messageMap.get("data");

        switch (command) {
            case Command.CREAR_PARTIDA:
                handleCrearPartida();
                break;
            case Command.MOVER_FICHA:
                handleMoverFicha();
                break;
            case Command.REGISTRAR_JUGADOR:
                handleRegistrarJugador(data);
        }
    } catch (JsonSyntaxException e) {
        System.out.println("Error al procesar mensaje: " + e.getMessage());
    }
}


    private void notifyClients(String message) {
    for (ClientHandler client : clientHandlers) { 
        client.sendMessage(message);
    }
}
    
    private void handleCrearPartida() {
        this.controlador.crearJuego(this);
    }

    private void handleRegistrarJugador(Object data) {
        Gson gson = new Gson();
        JugadorDTO jugadorDTO = gson.fromJson(data.toString(), JugadorDTO.class);
        System.out.println("REGISTRANDO JUGADOR: "  + jugadorDTO.getNombre());
        this.controlador.registrarJugador(this, jugadorDTO);
        
    } 
    
    private void handleMoverFicha() {
        System.out.println("Moviendo ficha...");
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
