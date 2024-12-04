/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import dtos.JugadorDTO;
import entidades.Juego;
import mensajes.Mensaje;
import mensajes.ReqResponderSolicitudInicio;

/**
 *
 * @author carlo
 */
public class ExpertoResponderSolicitudInicio implements Experto {

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
        ReqResponderSolicitudInicio res = (ReqResponderSolicitudInicio) mensaje;
        JugadorDTO jugador = res.getJugador();
        Boolean respuesta = res.isRes();
        juego.manejarRespuestasConfirmacion(jugador, respuesta);
    }
    
}
