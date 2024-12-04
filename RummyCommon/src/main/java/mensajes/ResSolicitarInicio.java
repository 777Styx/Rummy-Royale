/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import dtos.JugadorDTO;

/**
 *
 * @author carlo
 */
public class ResSolicitarInicio extends Mensaje {
    
    private JugadorDTO solicitante;
    
    public ResSolicitarInicio(String comando) {
        super(comando);
    }

    public JugadorDTO getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(JugadorDTO solicitante) {
        this.solicitante = solicitante;
    }
    
    
}
