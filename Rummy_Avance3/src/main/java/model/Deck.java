package model;

import java.util.Random;

/**
 *
 * @author julli
 */
public class Deck {

    private static Deck instance;

    public Deck() {
    }

    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    public void drawChip() {
        Random random = new Random();
        int chipNumber = 1 + random.nextInt(13);
        String[] colors = {"Rojo", "Verde", "Azul", "Amarillo"};
        String chipColor = colors[random.nextInt(colors.length)];
        System.out.println("Carta: " + chipNumber + " " + chipColor);
    }
}
