/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverCarlos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Map;
import mensajes.Mensaje;
import mensajes.MsgConfigurarPartida;
import mensajes.MsgRegistroJugador;

/**
 *
 * @author carlo
 */
public class ProcesadorMensaje {
    private ClientHandler clientHandler;
    private ManejadorComando manejadorComando;

    public ProcesadorMensaje(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        this.manejadorComando = new ManejadorComando(clientHandler);
    }
    
    public void procesarMensaje(String inputLine) {
        try {
            Mensaje mensaje = MessageDeserializer.deserializeMessage(inputLine);
            manejadorComando.manejarComando(mensaje);
            
        } catch (Exception e) {
            System.err.println("Error procesando mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
