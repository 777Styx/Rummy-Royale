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
public class ResRegistroJugador extends Mensaje {
    private final JugadorDTO jugador;
    
    public ResRegistroJugador(JugadorDTO jugador) {
        super("JUGADOR_REGISTRADO");
        this.jugador = jugador;
    }

    public JugadorDTO getJugador() {
        return jugador;
    }
    
    
}
