/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverCarlos;

import entidades.FichaComodin;
import entidades.IFicha;
import entidades.Juego;
import java.util.ArrayList;
import java.util.List;
import mensajes.Mensaje;
import mensajes.ReqConfigurarPartida;

/**
 *
 * @author carlo
 */
public class ExpertoCrearComodines implements Experto {

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
        ReqConfigurarPartida req = (ReqConfigurarPartida) mensaje;
        int numComodines = req.getJuego().getNumComodines();
        List<IFicha> comodines = new ArrayList<>();

        while (comodines.size() < numComodines) {
            comodines.add(new FichaComodin());
        }
        
        juego.setComodines(comodines);

        
    }
    
}
