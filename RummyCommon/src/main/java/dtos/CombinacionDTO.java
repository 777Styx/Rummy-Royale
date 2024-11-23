/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.List;

/**
 *
 * @author julli
 */
public class CombinacionDTO {

    public boolean esValida() {
        return false;
    }
    
    private List<FichaDTO> fichas;

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