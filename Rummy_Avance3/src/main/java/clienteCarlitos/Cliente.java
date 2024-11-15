package clienteCarlitos;

import common.Command;
import common.NetworkMessage;
import entidades.Jugador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Jugador jugador;
    private ClientListener listener;

    public Cliente(Socket socket) {
        try {
            this.socket = socket;
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("No se pudo conectar al servidor: " + e.getMessage());
        }
    }
    
    public boolean isConnected() {
        return socket.isConnected();
    }

    public void closeEverything() {
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

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void listenForMessage() {
        new Thread(() -> {
            while (socket.isConnected()) {
                try {
                    Object message = in.readObject();
                    if (message instanceof NetworkMessage) {
                        handleNetworkMessage((NetworkMessage) message);
                    } else {
                        // Para mantener compatibilidad con mensajes de texto existentes
                        String messageFromServer = (String) message;
                        if (listener != null) {
                            listener.onMessageReceived(messageFromServer);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    closeEverything();
                    break;
                }
            }
        }).start();
    }
    
    private void handleNetworkMessage(NetworkMessage message) {
        if (listener != null) {
            listener.onNetworkMessageReceived(message);
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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

    public void sendMessage() {
        try {
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                bufferedWriter.write("Client: " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendNetworkMessage(NetworkMessage networkMessage) {
        try{
            out.writeObject(networkMessage);
            out.flush();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendCommand(Command command, Map<String, Object> params) {
        try {
            NetworkMessage message = new NetworkMessage(command, params);
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            System.out.println("Error al enviar comando: " + e.getMessage());
            closeEverything();
        }
    }

//    public void sendMessageObject(Object objectoDTO) {
//
//        try {
//            this.outpuStream = new ObjectOutputStream(socket.getOutputStream());
//            outpuStream.writeObject(objectoDTO);
//            System.out.println("ENVIADO PANA");
//            //this.input = new ObjectInputStream(socket.getInputStream());
////            outpuStream.writeObject(objectoDTO);
//
//        } catch (IOException ex) {
//            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        try {
//            this.input = new ObjectInputStream(socket.getInputStream());
//            JuegoDTO juegoD = (JuegoDTO) input.readObject();
//            System.out.println("SE HIZO");
//        } catch (Exception e) {
//        }
//
//    }
}
