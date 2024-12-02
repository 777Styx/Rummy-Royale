/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteCarlitos;

import mensajes.Mensaje;
import mensajes.ResConfigurarPartida;
import mensajes.ResCrearPartida;
import mensajes.ResRegistroJugador;
import mensajes.ResUnirse;
import menuMVC.ModeloMenu;
import partidaMVC.ModeloJuego;

/**
 *
 * @author carlo
 */
public class ResponseManager {

    private ModeloMenu modeloMenu;
    private ModeloJuego modeloJuego;

    ResponseManager() {
        modeloMenu = ModeloMenu.getInstance();
        modeloJuego = ModeloJuego.getInstance();
    }

    protected void handleResponse(Mensaje mensaje) {
        if (mensaje instanceof ResCrearPartida) {
            ResCrearPartida res = (ResCrearPartida) mensaje;
            modeloMenu.notificar(res);
        }

        if (mensaje instanceof ResRegistroJugador) {
            if (mensaje.getComando().equals("JUGADOR_REGISTRADO")) {
                    modeloMenu.actualizarJugadores(mensaje);
                    modeloJuego.actualizarJugadores(mensaje);
            }
        }

        if (mensaje instanceof ResConfigurarPartida) {
            ResConfigurarPartida res = (ResConfigurarPartida) mensaje;
            modeloMenu.notificar(res);
        }
        
        if(mensaje instanceof ResUnirse) {
            ResUnirse res = (ResUnirse) mensaje;
            modeloMenu.notificar(res);
        }
    }

}
