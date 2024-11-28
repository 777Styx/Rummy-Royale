/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverCarlos;

import dtos.JuegoDTO;
import entidades.FichaComodin;
import entidades.FichaNumerica;
import entidades.IFicha;
import entidades.TipoFicha;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author carlo
 */
public class ExpertoFicha {
    
    
    
    public Map<String, List<IFicha>> crearFichas(JuegoDTO juego) {
        List<FichaComodin> comodines = new ArrayList<>();

        while (comodines.size() < juego.getNumComodines()) {
            comodines.add(new FichaComodin());
        }

        List<FichaNumerica> fichasNumericas = new ArrayList<>();
        int rango = juego.getRangoFichas();
        TipoFicha[] tiposFicha = TipoFicha.values();

        for (TipoFicha tipo : tiposFicha) {
            for (int numero = 1; numero <= rango; numero++) {
                fichasNumericas.add(new FichaNumerica(numero, tipo));
                fichasNumericas.add(new FichaNumerica(numero, tipo));
            }
        }
        
        Map<String, List<IFicha>> fichas = new HashMap<>();
        fichas.put("comodines", new ArrayList<>(comodines));
        fichas.put("fichasNumericas", new ArrayList<>(fichasNumericas));

        return fichas;
    }
}
