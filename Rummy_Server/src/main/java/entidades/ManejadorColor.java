/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author carlo
 */
public class ManejadorColor {
    private Map<TipoFicha, Color> colorMap;
    
    public ManejadorColor() {
        colorMap = new HashMap<>();
    }
    
    public void setColor(TipoFicha tipoFicha, Color color) {
        colorMap.put(tipoFicha, color);
    }
    
    public Color getColor(TipoFicha tipoFicha) {
        return colorMap.get(tipoFicha);
    }
    
 }
