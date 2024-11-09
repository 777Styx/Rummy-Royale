package entidades;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julli
 */
public class Jugador {

    private String nombre;
    private List<ManejadorColor> preferenciasColor;
    private ArrayList<IFicha> mano;
    private String avatar;
    
    public Jugador() {

    }

    public Jugador(String nombre, String avatar,  List<ManejadorColor> preferenciasColor) {
        this.nombre = nombre;
        this.avatar = avatar;
        this.preferenciasColor = preferenciasColor;
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

    public List<ManejadorColor> getPreferenciasColor() {
        return preferenciasColor;
    }

    public void setPreferenciasColor(List<ManejadorColor> preferenciasColor) {
        this.preferenciasColor = preferenciasColor;
    }

    public ArrayList<IFicha> getMano() {
        return mano;
    }

    public void setMano(ArrayList<IFicha> mano) {
        this.mano = mano;
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
