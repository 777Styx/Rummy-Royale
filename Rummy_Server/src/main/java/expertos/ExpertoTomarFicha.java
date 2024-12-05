/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expertos;

import entidades.Juego;
import mensajes.Mensaje;
import mensajes.ReqTomarFicha;

/**
 *
 * @author carlo
 */
public class ExpertoTomarFicha implements Experto {

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
        ReqTomarFicha req = (ReqTomarFicha) mensaje;
        juego.tomarFicha(req.getJugador());
    }

}
