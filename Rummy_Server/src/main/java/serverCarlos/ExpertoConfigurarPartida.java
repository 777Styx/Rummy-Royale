/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverCarlos;

import dtos.JuegoDTO;
import entidades.FichaComodin;
import entidades.FichaNumerica;
import entidades.IFicha;
import entidades.Juego;
import entidades.Mazo;
import entidades.TipoFicha;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mensajes.Mensaje;
import mensajes.ReqConfigurarPartida;

/**
 *
 * @author carlo
 */
public class ExpertoConfigurarPartida implements Experto {

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
        
        ReqConfigurarPartida req = (ReqConfigurarPartida) mensaje;
        int numComodines = req.getJuego().getNumComodines();
        int rangoFichas = req.getJuego().getRangoFichas();
        
        List<IFicha> comodines = new ArrayList<>();

        while (comodines.size() < numComodines) {
            comodines.add(new FichaComodin());
        }

        List<IFicha> fichasNumericas = new ArrayList<>();
        TipoFicha[] tiposFicha = TipoFicha.values();

        for (TipoFicha tipo : tiposFicha) {
            for (int numero = 1; numero <= rangoFichas; numero++) {
                fichasNumericas.add(new FichaNumerica(numero, tipo));
                fichasNumericas.add(new FichaNumerica(numero, tipo));
            }
        }

        Mazo mazo = new Mazo(fichasNumericas, comodines);
        juego.setMazo(mazo);
    }
    
}
