/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actualizaciones;

import dtos.JugadorDTO;

/**
 *
 * @author carlo
 */
public class JugadorDataActualizada implements ActualizacionJuego {

    private JugadorDTO jugador;

    public JugadorDataActualizada(JugadorDTO jugador) {
        this.jugador = jugador;
    }

    @Override
    public void aplicar(ViewJuego vista) {
        vista.mostrarJugadorData(jugador);
    }

    @Override
    public void aplicar(Vista vista) {
        if (vista instanceof ViewJuego) {
            aplicar((ViewJuego) vista);
        }
    }
}
