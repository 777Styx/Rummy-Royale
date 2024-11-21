package dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julli
 */
public class JugadorDTO {
    private String nombre;
    private List<ManejadorColorDTO> preferenciasColor;
    private ArrayList<FichaDTO> mano;
    private String avatar;

    public JugadorDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ManejadorColorDTO> getPreferenciasColor() {
        return preferenciasColor;
    }

    public void setPreferenciasColor(List<ManejadorColorDTO> preferenciasColor) {
        this.preferenciasColor = preferenciasColor;
    }

    public ArrayList<FichaDTO> getMano() {
        return mano;
    }

    public void setMano(ArrayList<FichaDTO> mano) {
        this.mano = mano;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    
    
    public void agregarFicha(FichaDTO remove) {
        
    }

}
