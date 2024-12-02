/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actualizaciones;

import dtos.JugadorDTO;
import java.util.List;
import partidaMVC.VistaJuego;

/**
 *
 * @author carlo
 */
public class JugadoresActualizados implements ActualizacionJuego {

    private List<JugadorDTO> jugadores;

    public JugadoresActualizados(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public void aplicar(ViewJuego vista) {
        System.out.println("JUGADORESACTUALIZADOS: ESTOY mostrando JUGDAORES");
        vista.mostrarJugadores(jugadores);
    }

    @Override
    public void aplicar(Vista vista) {
        if (vista instanceof ViewJuego) {
            aplicar((ViewJuego) vista);
        }
    }

}
