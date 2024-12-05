package Server;

import dtos.JuegoDTO;
import entidades.FichaNumerica;
import entidades.IFicha;
import entidades.Juego;
import entidades.TipoFicha;
import java.util.ArrayList;
import java.util.List;
import mensajes.Mensaje;
import mensajes.ReqConfigurarPartida;

/**
 *
 * @author carlo
 */
public class ExpertoCrearFichasNumericas implements Experto {

    @Override
    public void ejecutar(Juego juego, Mensaje mensaje) {
        System.out.println("Estoy creando fichas numericas");
        ReqConfigurarPartida req = (ReqConfigurarPartida) mensaje;
        int rangoFichas = req.getJuego().getRangoFichas();
        
        List<IFicha> fichasNumericas = new ArrayList<>();
        
        TipoFicha[] tiposFicha = TipoFicha.values();
        
        for (TipoFicha tipo : tiposFicha) {
            for (int numero = 1; numero <= rangoFichas; numero++) {
                fichasNumericas.add(new FichaNumerica(numero, tipo));
                fichasNumericas.add(new FichaNumerica(numero, tipo));
            }
        }
        
        juego.setFichasNumericas(fichasNumericas);
        juego.setConfigurado(false);
        
    }
    
}
