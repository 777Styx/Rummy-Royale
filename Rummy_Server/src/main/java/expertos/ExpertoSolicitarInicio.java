/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expertos;

import expertos.Experto;
import dtos.JugadorDTO;
import entidades.Juego;
import mensajes.Mensaje;
import mensajes.ReqSolicitarInicio;

/**
 *
 * @author carlo
 */
public class ExpertoSolicitarInicio implements Experto {

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
        ReqSolicitarInicio req = (ReqSolicitarInicio) mensaje;
        JugadorDTO jugadorDTO = req.getJugador();
        juego.solicitarInicio(jugadorDTO);
    }
    
}
