/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverCarlos;

import entidades.IFicha;
import entidades.Juego;
import entidades.Mazo;
import java.util.List;
import mensajes.Mensaje;

/**
 *
 * @author carlo
 */
public class ExpertoCrearMazo implements Experto {

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
        List<IFicha> fichasNumericas = juego.getFichasNumericas();
        List<IFicha> comodines = juego.getComodines();

        Mazo mazo = new Mazo(fichasNumericas, comodines);
        juego.setMazo(mazo);
    }
    
}
