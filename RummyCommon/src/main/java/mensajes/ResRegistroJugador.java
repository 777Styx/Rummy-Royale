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
    private final JugadorDTO jugadorNuevo;
    
    public ResRegistroJugador(String comando, JugadorDTO jugadorNuevo) {
        super(comando);
        this.jugadorNuevo = jugadorNuevo;
    }

    public JugadorDTO getJugadorNuevo() {
        return jugadorNuevo;
    }
    
    
}
