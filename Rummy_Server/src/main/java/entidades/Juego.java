package entidades;

import dtos.JugadorDTO;
import dtos.ManejadorColorDTO;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import serverCarlos.Controlador;

/**
 *
 * @author julli
 */
public class Juego extends Observable {

    private ArrayList<Jugador> jugadores;
    private Tablero tablero;
    private boolean partidaActiva = false;
    private static Juego instance;

    private Juego() {
        this.jugadores = new ArrayList<>();
        this.partidaActiva = false;
    }

    public static synchronized Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
            System.out.println("Nueva instancia de Juego creada.");
        }
        return instance;
    }

    public boolean validarAvatarNoUsado(String avatar) {
        for (Jugador jugador : jugadores) {
            if (jugador.getAvatar().equals(avatar)) {
                return false;
            }
        }
        return true;
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public boolean isPartidaActiva() {
        return partidaActiva;
    }

    public synchronized void setPartidaActiva(boolean flag) {
        if (partidaActiva == true) {
            setChanged();
            notifyObservers("YA_CREADO");
        } else {
            this.partidaActiva = flag;
            setChanged();
            String estado = "CREADO";
            notifyObservers(estado);
        }
    }

    public synchronized void registrarJugador(JugadorDTO jugador) {
//        Jugador nuevoJugador = new Jugador();
//        nuevoJugador.setNombre(jugador.getNombre());
//        nuevoJugador.setAvatar(jugador.getAvatar());
//
//        // colores
//        List<ManejadorColor> manejadoresColor = new ArrayList<>();
//
//        ManejadorColorDTO colorDTO1 = jugador.getPreferenciasColor().get(0);
//        ManejadorColorDTO colorDTO2 = jugador.getPreferenciasColor().get(1);
//        ManejadorColorDTO colorDTO3 = jugador.getPreferenciasColor().get(2);
//        ManejadorColorDTO colorDTO4 = jugador.getPreferenciasColor().get(3);
//
//        Color color1 = new Color(colorDTO1.getColor().getColor());
//        Color color2 = new Color(colorDTO2.getColor().getColor());
//        Color color3 = new Color(colorDTO3.getColor().getColor());
//        Color color4 = new Color(colorDTO4.getColor().getColor());
//
//        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO1, new ColorCustom(color1)));
//        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO2, new ColorCustom(color2)));
//        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO3, new ColorCustom(color3)));
//        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO4, new ColorCustom(color4)));
//        nuevoJugador.setPreferenciasColor(manejadoresColor);

    }

}
