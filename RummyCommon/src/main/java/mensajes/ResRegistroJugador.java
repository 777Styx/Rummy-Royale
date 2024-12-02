/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import dtos.JugadorDTO;
import java.util.List;

/**
 *
 * @author carlo
 */
public class ResRegistroJugador extends Mensaje {
    private final List<JugadorDTO> jugadores;
    
    public ResRegistroJugador(String comando, List<JugadorDTO> jugadores) {
        super(comando);
        this.jugadores = jugadores;
    }

    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }
    
    
}
