/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverCarlos;

import com.google.gson.Gson;
import java.util.Map;

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
    public void procesarMensaje(String message) {
        try {
            Gson gson = new Gson();
            Map<String, Object> messageMap = gson.fromJson(message, Map.class);

            String command = (String) messageMap.get("command");
            Object data = messageMap.get("data");

            manejadorComando.manejarComando(command, data);
        } catch (Exception e) {
            System.out.println("Error al procesar mensaje: " + e.getMessage());
        }
    }
    
}
