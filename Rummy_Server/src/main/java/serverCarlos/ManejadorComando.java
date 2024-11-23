/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverCarlos;

import com.google.gson.Gson;
import common.Command;
import dtos.JugadorDTO;

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
    
    public void manejarComando(String command, Object data) {
        switch (command) {
            case Command.CREAR_PARTIDA:
                handleCrearPartida();
                break;
            case Command.REGISTRAR_JUGADOR:
                handleRegistrarJugador(data);
                break;
            default:
                clientHandler.sendMessage("Comando no reconocido: " + command);
        }
    }
    
     private void handleCrearPartida() {
        controlador.crearJuego(clientHandler);
    }

    private void handleRegistrarJugador(Object data) {
        Gson gson = new Gson();
        JugadorDTO jugadorDTO = gson.fromJson(data.toString(), JugadorDTO.class);
        controlador.registrarJugador(clientHandler, jugadorDTO);
    }
}
