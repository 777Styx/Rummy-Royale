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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FichaNumerica{");
        sb.append("numero=").append(numero);
        sb.append(", color=").append(color);
        sb.append('}');
        return sb.toString();
    }

   

}
