/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlo
 */
public class ResUnirse extends Mensaje{
    
    private List<String> avatarsDisponibles;
    
    public ResUnirse(String comando) {
        super(comando);
        this.avatarsDisponibles = new ArrayList<>();
    }

    public List<String> getAvatarsDisponibles() {
        return avatarsDisponibles;
    }

    public void setAvatarsDisponibles(List<String> avatarsDisponibles) {
        this.avatarsDisponibles = avatarsDisponibles;
    }
    
    
}
