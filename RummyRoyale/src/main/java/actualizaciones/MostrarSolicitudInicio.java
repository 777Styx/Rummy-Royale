/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actualizaciones;

/**
 *
 * @author carlo
 */
public class MostrarSolicitudInicio implements ActualizacionJuego{

    private String solicitante;

    public MostrarSolicitudInicio(String solicitante) {
        this.solicitante = solicitante;
    }
    
    
    
    @Override
    public void aplicar(ViewJuego vista) {
        vista.mostrarSolicitudInicio(solicitante);
    }

    @Override
    public void aplicar(Vista vista) {
        if (vista instanceof ViewJuego) {
            aplicar((ViewJuego) vista);
        }
    }

   
}
