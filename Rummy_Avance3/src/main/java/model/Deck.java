/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
        int chipNumber = 1 + (int) (Math.random() * 13);
        String[] colors = {"Rojo", "Verde", "Azul", "Amarillo"};
        String chipColor = colors[(int) (Math.random() * colors.length)];
        System.out.println("Carta: " + chipNumber + " " + chipColor);
    }
}
