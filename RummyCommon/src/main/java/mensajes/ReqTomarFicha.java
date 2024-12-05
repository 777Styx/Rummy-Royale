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
public class ReqTomarFicha extends Mensaje{
    
    private JugadorDTO jugador;
    
    public ReqTomarFicha(JugadorDTO jugador) {
        super("TOMAR_FICHA");
        this.jugador =  jugador;
    }

    public JugadorDTO getJugador() {
        return jugador;
    }

    public void setJugador(JugadorDTO jugador) {
        this.jugador = jugador;
    }
    
    
    
}
