/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteCarlitos;

import mensajes.Mensaje;
import mensajes.ResConfigurarPartida;
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

    ResponseManager() {
        modeloMenu = ModeloMenu.getInstance();
        modeloJuego = ModeloJuego.getInstance();
    }

    protected void handleResponse(Mensaje mensaje) {
        if (mensaje instanceof ResCrearPartida) {
            ResCrearPartida respuesta = (ResCrearPartida) mensaje;
            if (respuesta.getComando().equals("PARTIDA_CREADA")) {
                modeloMenu.notificar(respuesta.getComando());
            } else if (respuesta.getComando().equals("PARTIDA_NO_CREADA")) {
                //modeloMenu.notificar(respuesta.getComando());
                
                // Manejar que no se creo
            }
        }
        
        if (mensaje instanceof ResRegistroJugador) {
            if (mensaje.getComando().equals("JUGADOR_REGISTRADO")) {
                System.out.println("Cliente: tudo bem, novo jogador registrao");
                modeloMenu.agregarJugador(((ResRegistroJugador) mensaje).getJugadorNuevo());
                modeloJuego.agregarJugador(((ResRegistroJugador) mensaje).getJugadorNuevo());
            }
        }

        if (mensaje instanceof ResConfigurarPartida) {
            System.out.println("Cliente recibio que configuraron partida");
            ResConfigurarPartida res = (ResConfigurarPartida) mensaje;
            modeloMenu.notificar(res.getComando());
        }
    }

}
