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
public class AvatarsActualizados implements ActualizacionRegistro {
    private List<String> avatars;

    public AvatarsActualizados(List<String> avatars) {
        this.avatars = avatars;
    }
    @Override
    public void aplicar(Vista vista) {
        if (vista instanceof ViewRegistro) {
            aplicar((ViewRegistro) vista);
        }
    }

    @Override
    public void aplicar(ViewRegistro vista) {
        System.out.println("estou aktualizando avatarssssssss");
        vista.mostrarAvatars(avatars);
    }
}
