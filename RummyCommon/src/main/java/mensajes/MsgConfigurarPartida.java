/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import dtos.JuegoDTO;

/**
 *
 * @author carlo
 */
public class MsgConfigurarPartida extends Mensaje{
    
    private final JuegoDTO juego;
    
    public MsgConfigurarPartida(JuegoDTO juego) {
        super("CONFIGURAR_PARTIDA");
        this.juego = juego;
    }
    
    public JuegoDTO getJuego(){
        return juego;
    }
    
}
