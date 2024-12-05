package dtos;

import java.util.List;

/**
 *
 * @author julli
 */
public class TableroDTO {
    private List<CombinacionDTO> combinaciones;

    public TableroDTO() {
    }
    
    public void agregarCombinacion(CombinacionDTO combinacionDTO) {
        this.combinaciones.add(combinacionDTO);
    }
    
    public List<CombinacionDTO> getCombinaciones() {
        return combinaciones;
    }

    public void setCombinaciones(List<CombinacionDTO> combinaciones) {
        this.combinaciones = combinaciones;
    }
}
