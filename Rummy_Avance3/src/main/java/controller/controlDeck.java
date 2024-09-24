package controller;

import model.Chip;
import model.Deck;

/**
 *
 * @author julli
 */
public class controlDeck {

    private Deck deck;

    public controlDeck() {
        this.deck = Deck.getInstance();
    }

    public void generateChip() {
        deck.drawChip();
    }
    
    
}
