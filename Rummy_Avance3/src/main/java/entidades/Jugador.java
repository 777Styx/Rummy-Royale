package entidades;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author julli
 */
public class Jugador {

    private String nombre;
    private String avatar = "";
    private ManejadorColor manejadorColor;   
    private ArrayList<IFicha> mano;

    public Jugador(String nombre, String avatar) {
        this.nombre = nombre;
        this.avatar = avatar;
        this.manejadorColor = new ManejadorColor();
        this.mano = new ArrayList<>();
    }

    /**
     *
     * @param ficha: ficha que se agregara a la mano(fichas pertenecientes) del
     * jugador
     */
    public void agregarFicha(IFicha ficha) {
        mano.add(ficha);
    }

    /**
     *
     * @return cantidad de fichas pertenecientes al jugador
     */
    public int obtenerTamanoDeMano() {
        return mano.size();
    }

    /**
     *
     * @return nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return avatar del jugador
     */
    public String getAvatar() {
        return avatar;
    }

    public ManejadorColor getManejadorColor() {
        return manejadorColor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador{");
        sb.append("nombre=").append(nombre);
        sb.append(", avatar=").append(avatar);
        sb.append(", mano=").append(mano);
        sb.append('}');
        return sb.toString();
    }

}
