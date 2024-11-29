/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverCarlos;

import dtos.JugadorDTO;
import dtos.ManejadorColorDTO;
import entidades.ColorCustom;
import entidades.Juego;
import entidades.Jugador;
import entidades.ManejadorColor;
import entidades.TipoFicha;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import mensajes.Mensaje;
import mensajes.ReqRegistroJugador;
import mensajes.ResRegistroJugador;

/**
 *
 * @author carlo
 */
public class ExpertoRegistrar implements Experto {

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
        ReqRegistroJugador req = (ReqRegistroJugador) mensaje;
        JugadorDTO jugadorDTO = req.getJugador();
        
        
        
        juego.agregarJugador(jugadorDTO);
    }
    
}