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
public class ResIniciarPartida extends Mensaje {
    
    private final List<JugadorDTO> jugadores;
    private final int indiceActual;
    
    public ResIniciarPartida(String comando, List<JugadorDTO> jugadores, int indiceActual) {
        super(comando);
        this.jugadores = jugadores;
        this.indiceActual = indiceActual;
    }

    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }

    public int getIndiceActual() {
        return indiceActual;
    }
    
    
    
}
