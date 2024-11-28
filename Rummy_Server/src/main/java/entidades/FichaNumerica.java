package entidades;

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

    public TipoFicha getTipoFicha() {
        return tipoFicha;
    }
}
