/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverCarlos;

import entidades.FichaComodin;
import entidades.FichaNumerica;
import entidades.IFicha;
import entidades.Mazo;
import java.util.List;

/**
 *
 * @author carlo
 */
public class ExpertoMazo {
    public Mazo crearMazo(List<IFicha> numericas, List<IFicha> comodines) {
        Mazo mazo = new Mazo(numericas, comodines);
        return mazo;
    }
}
