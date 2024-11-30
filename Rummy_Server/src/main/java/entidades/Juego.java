package entidades;

import dtos.JugadorDTO;
import dtos.ManejadorColorDTO;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import mensajes.Mensaje;
import mensajes.ResConfigurarPartida;
import mensajes.ResCrearPartida;
import mensajes.ResRegistroJugador;

/**
 *
 * @author julli
 */
public class Juego extends Observable {

    private ArrayList<Jugador> jugadores;

    private Tablero tablero;
    private boolean partidaActiva = false;
    private static Juego instance;
    private ArrayList<String> avatarsDisponibles;
    private final List<String> avatars = Arrays.asList("creeper", "pig", "steve", "villager");
    private Mazo mazo;
    private EstadoJuego estado;
    private List<IFicha> fichasNumericas = new ArrayList<>();
    private List<IFicha> comodines = new ArrayList<>();

    public List<IFicha> getComodines() {
        return comodines;
    }

    public void setComodines(List<IFicha> comodines) {
        this.comodines = comodines;
    }

    public enum EstadoJuego {
        INICIO,
        CREADO,
        CONFIGURADO,
        EN_CURSO,
        FINALIZADO
    }

    private Juego() {
        this.jugadores = new ArrayList<>();
        this.partidaActiva = false;
        this.avatarsDisponibles = new ArrayList<>(avatars);
        this.estado = EstadoJuego.INICIO;
    }

    public static synchronized Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
            System.out.println("Nueva instancia de Juego creada.");
        }
        return instance;
    }

    public List<IFicha> getFichasNumericas() {
        return fichasNumericas;
    }

    public void setFichasNumericas(List<IFicha> fichasNumericas) {
        this.fichasNumericas = fichasNumericas;
    }

    public void removerAvatar(String avatar) {
        avatarsDisponibles.remove(avatar);
    }

    public void agregarJugador(JugadorDTO jugadorDTO) {
        if (jugadores.size() < 3) {
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
            jugadores.add(jugador);
            removerAvatar(jugador.getAvatar());
            System.out.println("Soy Juego: si se registro un jugador");
            setChanged();
            notifyObservers(new ResRegistroJugador("JUGADOR_REGISTRADO",jugadorDTO));
        } else {
            setChanged();
            notifyObservers(new ResRegistroJugador("JUGADOR_NO_REGISTRADO", null));
            System.out.println("Soy Juego: no se registro un jugador");
        }

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

    public ArrayList<String> getAvatarsDisponibles() {
        return avatarsDisponibles;
    }

    public void setAvatarsDisponibles(ArrayList<String> avatarsDisponibles) {
        this.avatarsDisponibles = avatarsDisponibles;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
        this.estado = EstadoJuego.CONFIGURADO;
        setChanged();
        notifyObservers(new ResConfigurarPartida("PARTIDA_CONFIGURADA"));
        System.out.println("El mazo esta vacio?: " + mazo.estaVacio());
    }

    public synchronized void setPartidaActiva(boolean flag) {
        if (partidaActiva == true) {
            setChanged();
            notifyObservers(new ResCrearPartida("PARTIDA_NO_CREADA"));
        } else {
            this.partidaActiva = flag;
            setChanged();
            notifyObservers(new ResCrearPartida("PARTIDA_CREADA"));
            this.estado = EstadoJuego.CREADO;
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
