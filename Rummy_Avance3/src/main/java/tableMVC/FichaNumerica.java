package tableMVC;

import java.awt.Color;

/**
 *
 * @author julli
 */
public class FichaNumerica implements Ficha {

    private int numero; 
    private Color color; 

    public FichaNumerica(int numero, Color color) {
        this.numero = numero;
        this.color = color;
    }

    public int getNumero() {
        return numero;
    }

    public Color getColor() {
        return color;
    }

  

}
