/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actualizaciones;

/**
 *
 * @author carlo
 */
public class MostrarMensaje implements ActualizacionJuego {

    private String mensaje;

    public MostrarMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    @Override
    public void aplicar(ViewJuego vista) {
        vista.mostrarMensaje(mensaje);
    }

    @Override
    public void aplicar(Vista vista) {
        if (vista instanceof ViewJuego) {
            aplicar((ViewJuego) vista);
        }
    }
    
}

