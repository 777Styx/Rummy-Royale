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
public class ReqResponderSolicitudInicio extends Mensaje{
    
    private final boolean res;
    private final JugadorDTO jugador;
    
    public ReqResponderSolicitudInicio(Boolean res, JugadorDTO jugador) {
        super("RESPONDER_SOLICITUD_INICIO");
        this.res = res;
        this.jugador = jugador;
    }

    public boolean isRes() {
        return res;
    }

    public JugadorDTO getJugador() {
        return jugador;
    }
    
    
}
