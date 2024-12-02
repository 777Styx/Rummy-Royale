/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package actualizaciones;

import dtos.JugadorDTO;
import java.util.List;

/**
 *
 * @author carlo
 */
public interface ViewJuego extends Vista{
    void mostrarJugadores(List<JugadorDTO> jugadores);
}
