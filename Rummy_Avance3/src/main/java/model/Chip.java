package model;

/**
 *
 * @author julli
 */
public class Chip {

    private int number;
    private String color;

    public Chip(int numero, String color) {
        this.number = numero;
        this.color = color;
    }

    public int getNumero() {
        return number;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Carta: " + number + " de " + color;
    }
}
