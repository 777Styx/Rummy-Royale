/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverCarlos;

import com.google.gson.Gson;
import common.Command;
import dtos.JugadorDTO;
import mensajes.Mensaje;
import mensajes.MsgRegistroJugador;

/**
 *
 * @author carlo
 */
public class ManejadorComando {
    private ClientHandler clientHandler;
    private Controlador controlador;

    public ManejadorComando(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        this.controlador = Controlador.getInstance();
    }
    
    public void manejarComando(Mensaje mensaje) {
        
        if(mensaje == null) {
            clientHandler.sendMessage("Mensaje nulo");
            return;
        }
        
        switch (mensaje.getComando()) {
            case Command.CREAR_PARTIDA:
                handleCrearPartida();
                break;
            case Command.REGISTRAR_JUGADOR:
                handleRegistrarJugador((MsgRegistroJugador) mensaje);
                break;
            default:
                clientHandler.sendMessage("Comando no reconocido: " + mensaje.getComando());
        }
    }
    
     private void handleCrearPartida() {
        controlador.crearJuego(clientHandler);
    }

    private void handleRegistrarJugador(MsgRegistroJugador mensaje) {
       JugadorDTO jugador = mensaje.getJugador();
       controlador.registrarJugador(clientHandler, jugador);
    }
}
