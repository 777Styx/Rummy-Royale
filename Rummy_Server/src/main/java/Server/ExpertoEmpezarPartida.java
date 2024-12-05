/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import entidades.Juego;
import mensajes.Mensaje;

/**
 *
 * @author carlo
 */
public class ExpertoEmpezarPartida implements Experto{

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
        juego.empezarPartida();
        juego.setPartidaEmpezada(true, mensaje);
    }
    
}
