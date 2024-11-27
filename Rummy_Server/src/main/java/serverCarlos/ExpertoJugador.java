package serverCarlos;

import dtos.JugadorDTO;
import dtos.ManejadorColorDTO;
import entidades.ColorCustom;
import entidades.Juego;
import entidades.Jugador;
import entidades.ManejadorColor;
import entidades.TipoFicha;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import mensajes.Mensaje;
import mensajes.ResRegistroJugador;

/**
 *
 * @author carlo
 */
public class ExpertoJugador {

    Juego juego = Juego.getInstance();

    public ExpertoJugador() {

    }

    public synchronized void registrarJugador(JugadorDTO jugadorDTO) {

        List<ManejadorColor> manejadoresColor = new ArrayList<>();

        ManejadorColorDTO mc1 = jugadorDTO.getPreferenciasColor().get(0);
        ManejadorColorDTO mc2 = jugadorDTO.getPreferenciasColor().get(1);
        ManejadorColorDTO mc3 = jugadorDTO.getPreferenciasColor().get(2);
        ManejadorColorDTO mc4 = jugadorDTO.getPreferenciasColor().get(3);

        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO1, new ColorCustom(new Color(mc1.getColor().getColor()))));
        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO2, new ColorCustom(new Color(mc2.getColor().getColor()))));
        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO3, new ColorCustom(new Color(mc3.getColor().getColor()))));
        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO4, new ColorCustom(new Color(mc4.getColor().getColor()))));

        Jugador jugador = new Jugador(
                jugadorDTO.getNombre(),
                jugadorDTO.getAvatar(),
                manejadoresColor
        );

        juego.getJugadores().add(jugador);

        Mensaje mensaje = new ResRegistroJugador(jugadorDTO);
        juego.notifyObservers(mensaje);
        // juego.notifyObservers("JUGADOR_REGISTRADO");

    }
}
