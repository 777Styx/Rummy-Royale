/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actualizaciones;

import dtos.JugadorDTO;
import java.util.List;

/**
 *
 * @author carlo
 */
public class MostrarMano implements ActualizacionJuego {

    private JugadorDTO jugador;

    public MostrarMano(JugadorDTO jugador) {
        this.jugador = jugador;
    }
    
    @Override
    public void aplicar(ViewJuego vista) {
        vista.mostrarMano(jugador);
    }

    @Override
    public void aplicar(Vista vista) {
        if (vista instanceof ViewRegistro) {
            aplicar((ViewRegistro) vista);
        }
    }
    
}
