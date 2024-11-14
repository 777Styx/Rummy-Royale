package serverCarlos;

import common.NetworkMessage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author carlo
 */
public class ClientHandler implements Runnable {

    private static final List<ClientHandler> clientHandlers = Collections.synchronizedList(new ArrayList<>());
    private final Socket socket;
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;
    private final ObjectInputStream objectInputStream;
  

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        clientHandlers.add(this);
        broadcastMessage("SERVER: Un nuevo jugador entroooo");
    }

    @Override
    public void run() {
        try {
            while (socket.isConnected()) {
                NetworkMessage networkMessage = (NetworkMessage) objectInputStream.readObject();
                handleNetworkMessage(networkMessage);
            }
        } catch (IOException | ClassNotFoundException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (clientHandler != this) {
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("SERVER: Alguien c fue!");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private void handleNetworkMessage(NetworkMessage networkMessage) {
        String command = networkMessage.getCommand();
        
        switch (command) {
            case "DRAW":
                // Maneja la acción DRAW
                break;
            case "PLAY":
                // Maneja la acción PLAY
                break;
            case "END_TURN":
                // Maneja la acción END_TURN
                break;
            default:
                System.out.println("Comando no reconocido: " + command);
        }
        
        broadcastMessage("Comando recibido: " + command);
    }
}
