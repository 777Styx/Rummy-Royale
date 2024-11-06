/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverCarlos;

import java.net.Socket;

/**
 *
 * @author carlo
 */
public class ClientHandler implements Runnable {
    
    private final Socket socketClient;
    private final Server servidor;

    public ClientHandler(Socket socketClient, Server servidor) {
        this.socketClient = socketClient;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        
    }
    
    
    
}
