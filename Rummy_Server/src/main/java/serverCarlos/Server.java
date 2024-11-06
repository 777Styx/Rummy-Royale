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
import servidor.Servidor;

/**
 *
 * @author carlo
 */
public class Server {

    private static final int MAX_JUGADORES = 4;
    private final Set<Socket> jugadoresConectados = Collections.synchronizedSet(new HashSet<>());
    private final int puerto;
    private ServerSocket servidorSocket;

    public Server(int puerto) {
        this.puerto = puerto;
        this.servidorSocket = servidorSocket;
    }
    
    public void iniciar() {
        try {
            while(!servidorSocket.isClosed()) {
                Socket socket = servidorSocket.accept();
                System.out.println("Un nuevo jugador se ha conectado!");
                ClientHandler jugador = new ClientHandler(socket);
                
                Thread thread = new Thread(jugador);
                thread.start();
            }
        } catch(IOException e) {
        
        }
    }
    
    public void detener() {
        try {
            if(servidorSocket != null) {
                servidorSocket.close();
            }
            System.out.println("Servidor detenido.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
    public Set<Socket> getJugadoresConectados() {
        return jugadoresConectados;
    }


    public void removerJugador(Socket jugador) {
        jugadoresConectados.remove(jugador);
        System.out.println("Jugador desconectado. Jugadores activos: " + jugadoresConectados.size());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic 
        int puerto = 3030;
        Server servidor = new Server(puerto);
        servidor.iniciar(); // Inicia el servidor
        System.out.println("Server running...");
        
    }
    
}   
