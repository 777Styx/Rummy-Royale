package clienteCarlitos;

import dto.JuegoDTO;
import entidades.Juego;
import entidades.Jugador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
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
    private Jugador jugador;

    public Cliente(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                while (socket.isConnected()) {
                    try {
                        msgFromGroupChat = bufferedReader.readLine();
                        System.out.println(msgFromGroupChat);
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
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
