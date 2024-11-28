/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import dtos.JugadorDTO;

/**
 *
 * @author carlo
 */
public class ReqRegistroJugador extends Mensaje {
    private final JugadorDTO jugador;
    
    public ReqRegistroJugador(JugadorDTO jugador) {
        super("REGISTRAR_JUGADOR");
        this.jugador = jugador;
    }
    
    public JugadorDTO getJugador() {
        return jugador;
    }   
}
