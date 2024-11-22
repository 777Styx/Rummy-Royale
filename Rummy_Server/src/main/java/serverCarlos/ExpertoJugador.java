/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverCarlos;

import dtos.JugadorDTO;
import entidades.Juego;

/**
 *
 * @author carlo
 */
public class ExpertoJugador {
    public ExpertoJugador() {
        
    }
    
    public void registrarJugador(JugadorDTO jugador) {
        Juego juego = Juego.getInstance();
        juego.registrarJugador(jugador);
    }
}
