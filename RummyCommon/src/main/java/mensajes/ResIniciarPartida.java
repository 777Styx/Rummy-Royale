/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import dtos.JugadorDTO;
import dtos.TableroDTO;
import java.util.List;

/**
 *
 * @author carlo
 */
public class ResIniciarPartida extends Mensaje {
    
    private final List<JugadorDTO> jugadores;
    private final int indiceActual;
    private final TableroDTO tableroDTO;
    
    public ResIniciarPartida(String comando, List<JugadorDTO> jugadores, int indiceActual, TableroDTO tableroDTO) {
        super(comando);
        this.jugadores = jugadores;
        this.indiceActual = indiceActual;
        this.tableroDTO = tableroDTO;
    }

    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }

    public int getIndiceActual() {
        return indiceActual;
    }

    public TableroDTO getTableroDTO() {
        return tableroDTO;
    }
}
