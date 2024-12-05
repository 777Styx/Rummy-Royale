package entidades;

import dtos.ColorCustomDTO;
import dtos.JugadorDTO;
import dtos.ManejadorColorDTO;
import dtos.TipoFichaDTO;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.UUID;
import mensajes.Mensaje;
import mensajes.ResConfigurarPartida;
import mensajes.ResCrearPartida;
import mensajes.ResRegistroJugador;
import mensajes.ResSolicitarInicio;
import mensajes.ResUnirse;

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
    private boolean solicitudEnCurso;
    private UUID idSolicitudInicio; // por si hay que diferenciar las solicitudes, no creo
    private Map<JugadorDTO, Boolean> respuestasSolicitud;
    
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
        EN_PROGRESO,
        FINALIZADO
    }

    private Juego() {
        this.jugadores = new ArrayList<>();
        this.partidaActiva = false;
        this.avatarsDisponibles = new ArrayList<>(avatars);
        this.estado = EstadoJuego.INICIO;
        this.solicitudEnCurso = false;
        this.respuestasSolicitud = new HashMap<>();
    }

    public static synchronized Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
            System.out.println("Nueva instancia de Juego creada.");
        }
        return instance;
    }

    public synchronized void agregarJugador(JugadorDTO jugadorDTO) {
        if (this.jugadores.size() < 4) {
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
                    manejadoresColor,
                    generarIdJugador(jugadorDTO.getNombre())
            );
            this.jugadores.add(jugador);
            removerAvatar(jugador.getAvatar());

            List<JugadorDTO> jugadoresDTO = new ArrayList<>();
            for (Jugador j : this.jugadores) {
                JugadorDTO jDTO = new JugadorDTO();
                List<ManejadorColorDTO> mColores = new ArrayList<>();
                mColores.add(new ManejadorColorDTO(TipoFichaDTO.TIPO1, new ColorCustomDTO(j.getPreferenciasColor().get(0).getColor().getColor().getRGB())));
                mColores.add(new ManejadorColorDTO(TipoFichaDTO.TIPO1, new ColorCustomDTO(j.getPreferenciasColor().get(1).getColor().getColor().getRGB())));
                mColores.add(new ManejadorColorDTO(TipoFichaDTO.TIPO1, new ColorCustomDTO(j.getPreferenciasColor().get(2).getColor().getColor().getRGB())));
                mColores.add(new ManejadorColorDTO(TipoFichaDTO.TIPO1, new ColorCustomDTO(j.getPreferenciasColor().get(3).getColor().getColor().getRGB())));
                jDTO.setNombre(j.getNombre());
                jDTO.setAvatar(j.getAvatar());
                jDTO.setPreferenciasColor(mColores);
                jDTO.setId(j.getId());
                jugadoresDTO.add(jDTO);
            }
            setChanged();
            notifyObservers(new ResRegistroJugador("JUGADOR_REGISTRADO", jugadoresDTO, jugador.getId()));
            
            if(jugadores.size() == 4) {
                // iniciar partida aqui tmb
            }
        } else {
            setChanged();
            notifyObservers(new ResRegistroJugador("JUGADOR_NO_REGISTRADO", null, null));
        }

    }

    public synchronized void unirse() {
        if (this.estado.equals(EstadoJuego.CONFIGURADO)) {
            setChanged();
            ResUnirse res = new ResUnirse("JUGADOR_UNIDO");
            res.setAvatarsDisponibles(avatarsDisponibles);
            notifyObservers(res);
        } else {
            setChanged();
            notifyObservers(new ResUnirse("JUGADOR_NO_UNIDO"));
        }
    }

    public synchronized void solicitarInicio(JugadorDTO solicitante) {
        if (solicitudEnCurso) {
            System.out.println("hay una soliciutd en curso");
            ResSolicitarInicio res = new ResSolicitarInicio("SOLICITUD_EN_CURSO");
            setChanged();
            notifyObservers(res);
        } else {
            solicitudEnCurso = true;
            idSolicitudInicio = UUID.randomUUID();
            ResSolicitarInicio res2 = new ResSolicitarInicio("SOLICITUD_ENVIADA");
            res2.setSolicitante(solicitante);
            setChanged();
            notifyObservers(res2);
        }
    }
    
    public synchronized void manejarRespuestasConfirmacion(JugadorDTO jugador, Boolean respuesta) {
        if(!solicitudEnCurso) {
            System.out.println("no hay solicitud en cursooo");
            return;
        } 
        
        respuestasSolicitud.put(jugador, respuesta);
        if(respuestasSolicitud.size() == jugadores.size() - 1) {
            boolean todosAceptaron = respuestasSolicitud.values().stream().allMatch(r -> r);
            if(todosAceptaron) {
                System.out.println("INICIO DE JUEGOOOOOO");
                setChanged();
                notifyObservers("INICIANDO_JUEGO");
            } else {
                System.out.println("No todos aceptaron");
            }
            
            respuestasSolicitud.clear();
            solicitudEnCurso = false;
        }
    }
    
    public void repartirFichas(){
        for(Jugador jugador : jugadores) {
            ArrayList<IFicha> manoJugador = new ArrayList<>();
            while(manoJugador.size() < 13) {
                manoJugador.add(this.mazo.tomarFicha());
            }
            
            jugador.setMano(manoJugador);
            System.out.println("numero de cartas que tiene " + jugador.getNombre()+ ": " + manoJugador.size());
        }
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
        ResConfigurarPartida res = new ResConfigurarPartida("PARTIDA_CONFIGURADA");
        res.setAvatarsDisponibles(avatarsDisponibles);
        notifyObservers(res);
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

    public String generarIdJugador(String nombreJugador) {
        String salt = UUID.randomUUID().toString().substring(0, 8);
        return nombreJugador + "_" + salt;
    }

}
