/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverCarlos;

import entidades.Juego;
import mensajes.Mensaje;
import mensajes.ReqUnirse;

/**
 *
 * @author carlo
 */
public class ExpertoUnirse implements Experto{

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
//        ReqUnirse req = (ReqUnirse) mensaje;
        juego.unirse();
        
    }
    
}
