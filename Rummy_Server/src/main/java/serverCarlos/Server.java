/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serverCarlos;

import entidades.Juego;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import mensajes.Mensaje;
import mensajes.MessageManager;

/**
 *
 * @author carlo
 */
public class Server {

    private static Server instance;
    private ServerSocket serverSocket;
    private ExecutorService pool;
    private boolean running;
    private static final int PORT = 5000;
    private List<ClientHandler> clients;

    private Server() {
        pool = Executors.newFixedThreadPool(4);
        clients = new CopyOnWriteArrayList<>();
    }

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }
    
    public void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            running = true;
            System.out.println("Servidor iniciado en puerto " + PORT);

            while (running) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + clientSocket.getInetAddress());
                ClientHandler handler = new ClientHandler(clientSocket, this);
                clients.add(handler);
                pool.execute(handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(Mensaje mensaje, ClientHandler sender) {
        
        //String jsonMessage = MessageManager.toJson(mensaje);
        
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(mensaje);
            } else {
                client.sendMessage(mensaje);
            }
        }
    }

    public void addClient(ClientHandler client) {
        clients.add(client);
    }
    
    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public static void main(String[] args) {
        Juego juego = Juego.getInstance();
        Controlador controlador = Controlador.getInstance();
        juego.addObserver(controlador);
        
        Server server = Server.getInstance();
        server.startServer();
    }

}
