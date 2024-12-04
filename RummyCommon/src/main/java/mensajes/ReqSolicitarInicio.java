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
public class ReqSolicitarInicio extends Mensaje{
    
    private final JugadorDTO jugador;
    
    public ReqSolicitarInicio(JugadorDTO jugador) {
        super("SOLICITAR_INICIO");
        this.jugador = jugador;
    }

    public JugadorDTO getJugador() {
        return jugador;
    }
}
