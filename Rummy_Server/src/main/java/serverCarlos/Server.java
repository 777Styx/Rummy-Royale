/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serverCarlos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author carlo
 */
public class Server {

    private static final int MAX_JUGADORES = 4;
    private final Set<ClientHandler> jugadoresConectados = Collections.synchronizedSet(new HashSet<>());
    private ServerSocket serverSocket;
    private Board board;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void iniciar() {
        try {
            while (!serverSocket.isClosed()) {
                if (jugadoresConectados.size() < MAX_JUGADORES) {
                    Socket socket = serverSocket.accept();
                    System.out.println("Un nuevo jugador se ha conectado!");

                    ClientHandler clientHandler = new ClientHandler(socket);
                    jugadoresConectados.add(clientHandler);
                    Thread thread = new Thread(clientHandler);
                    thread.start();
                } else {
                    System.out.println("Servidor lleno. No se pueden conectar más jugadores.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void detener() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            System.out.println("Servidor detenido.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<ClientHandler> getJugadoresConectados() {
        return jugadoresConectados;
    }

    public void removerJugador(ClientHandler jugador) {
        jugadoresConectados.remove(jugador);
        System.out.println("Jugador desconectado. Jugadores activos: " + jugadoresConectados.size());
    }

    public void crearJuego() {
        board.ponerJuego();
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2222);
        Server server = new Server(serverSocket);
        server.iniciar(); 
        System.out.println("Server running...");
    }
}
