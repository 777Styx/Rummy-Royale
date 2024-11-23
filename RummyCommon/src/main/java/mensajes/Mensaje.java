/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import java.io.Serializable;

/**
 *
 * @author carlo
 */
public abstract class Mensaje implements Serializable {
    private final String comando;

    public Mensaje(String comando) {
        this.comando = comando;
    }

    public String getComando() {
        return comando;
    }  
}
