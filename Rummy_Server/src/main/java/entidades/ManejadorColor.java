package entidades;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author carlo
 */
public class ManejadorColor {
    private TipoFicha tipoFicha;
    private ColorCustom color;
    
    public ManejadorColor(TipoFicha tipoFicha, ColorCustom color) {
        this.tipoFicha = tipoFicha;
        this.color = color;
    }

    public TipoFicha getTipoFicha() {
        return tipoFicha;
    }

    public void setTipoFicha(TipoFicha tipoFicha) {
        this.tipoFicha = tipoFicha;
    }

    public ColorCustom getColor() {
        return color;
    }

    public void setColor(ColorCustom color) {
        this.color = color;
    }
    
    
    
 }
