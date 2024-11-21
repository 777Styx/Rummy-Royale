/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

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
