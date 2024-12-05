package entidades;

import dtos.TipoFichaDTO;
import java.awt.Color;

/**
 *
 * @author julli
 */
public class FichaNumerica implements IFicha {

    private int numero;
    private final TipoFicha tipoFicha;

    public FichaNumerica(int numero, TipoFicha tipoFicha) { 
        this.numero = numero;
        this.tipoFicha = tipoFicha;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public TipoFicha getTipo() {
        return tipoFicha;
    }

    @Override
    public boolean isComodin() {
        return false;
    }
}
