package dtos;

import java.util.List;

/**
 *
 * @author Wilber
 */
public class MazoDTO {

    private List<FichaDTO> mazoFichas;

    public MazoDTO() {
        // Constructor vacío necesario para deserialización
    }

    public MazoDTO(List<FichaDTO> mazoFichas) {
        this.mazoFichas = mazoFichas;
    }

    public List<FichaDTO> getMazoFichas() {
        return mazoFichas;
    }

    public void setMazoFichas(List<FichaDTO> mazoFichas) {
        this.mazoFichas = mazoFichas;
    }

    @Override
    public String toString() {
        return "MazoDTO{"
                + "mazoFichas=" + mazoFichas
                + '}';
    }
}
