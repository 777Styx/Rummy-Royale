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
    private final String avatarJugadorNuevo;
    
    public ResRegistroJugador(String comando, List<JugadorDTO> jugadores, String avatarJugadorNuevo) {
        super(comando);
        this.jugadores = jugadores;
        this.avatarJugadorNuevo = avatarJugadorNuevo;
    }

    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }

    public String getAvatarJugadorNuevo() {
        return avatarJugadorNuevo;
    }
    
}
