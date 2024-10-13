package tableMVC;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author julli
 */
public class Jugador {

    // Nombre del jugador
    private String nombre;
    // Fichas que tiene el jugador en su mano      
    private ArrayList<Ficha> mano;

    public Jugador(String nombre, ColorFicha color) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
    }

    /**
     *
     * @param ficha: ficha que se agregara a la mano(fichas pertenecientes) del
     * jugador
     */
    public void agregarFicha(Ficha ficha) {
        mano.add(ficha);
    }

    /**
     *
     * @return cantidad de fichas pertenecientes al jugador
     */
    public int obtenerTamañoDeMano() {
        return mano.size();
    }

    /**
     *
     * @return nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

}
