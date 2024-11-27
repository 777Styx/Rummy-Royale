/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteCarlitos;

import mensajes.Mensaje;
import mensajes.ResCrearPartida;
import mensajes.ResRegistroJugador;
import menuMVC.ModeloMenu;
import partidaMVC.ModeloJuego;

/**
 *
 * @author carlo
 */
public class ResponseManager {

    private ModeloMenu modeloMenu;
    private ModeloJuego modeloJuego;
    // aqui ira lo de MessageListener

    ResponseManager() {
        modeloMenu = ModeloMenu.getInstance();
        modeloJuego = ModeloJuego.getInstance();
    }

    protected void handleResponse(Mensaje mensaje) {
        if (mensaje instanceof ResCrearPartida) {
            ResCrearPartida respuesta = (ResCrearPartida) mensaje;
            if (respuesta.getComando().equals("PARTIDA_CREADA")) {
                System.out.println("La partida fue creada exitosamente.");
                modeloMenu.updateEstadoJuego(respuesta.getComando());
            } else if (respuesta.getComando().equals("PARTIDA_NO_CREADA")) {
                System.out.println("No se pudo crear la partida.");
            }
        }

        if (mensaje instanceof ResRegistroJugador) {
            ResRegistroJugador respuesta = (ResRegistroJugador) mensaje;
            modeloMenu.seRegistroJugador();
            modeloJuego.agregarJugador(respuesta.getJugador());
        }
    }

}
