package dtos;

import java.util.List;

/**
 *
 * @author julli
 */
public class CombinacionDTO {

    private List<FichaDTO> fichas;
    
    public boolean esValida() {
        return false;
    }
    
    public CombinacionDTO() {
        // Constructor vacío necesario para deserialización
    }

    public CombinacionDTO(List<FichaDTO> fichas) {
        this.fichas = fichas;
    }

    public List<FichaDTO> getFichas() {
        return fichas;
    }

    public void setFichas(List<FichaDTO> fichas) {
        this.fichas = fichas;
    }

    @Override
    public String toString() {
        return "CombinacionDTO{" +
                "fichas=" + fichas +
                '}';
    }
    
    
   
}