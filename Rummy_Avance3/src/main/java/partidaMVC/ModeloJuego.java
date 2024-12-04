package partidaMVC;

import Cliente.Cliente;
import actualizaciones.JugadorDataActualizada;
import actualizaciones.JugadoresActualizados;
import actualizaciones.MostrarMensaje;
import actualizaciones.MostrarSolicitudInicio;
import dtos.CombinacionDTO;
import dtos.FichaDTO;
import dtos.JugadorDTO;
import dtos.TableroDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import mensajes.Mensaje;
import mensajes.ResRegistroJugador;
import mensajes.ResSolicitarInicio;

/**
 *
 * @author puerta
 */
public class ModeloJuego extends Observable {

    private List<JugadorDTO> jugadores;
    private List<FichaDTO> fichasDTO;
    private TableroDTO tableroDTO;
    private int indiceJugadorActual;
    private Random random;
    private static ModeloJuego instance;
    private JugadorDTO jugador;
    private Cliente cliente;
    

    private ModeloJuego(Cliente cliente) {
        this.cliente = cliente;
        jugadores = new ArrayList<>();
        fichasDTO = new ArrayList<>();
        tableroDTO = new TableroDTO();
        indiceJugadorActual = 0;
        random = new Random();
    }

    public static ModeloJuego getInstance(Cliente cliente) {
        return instance == null ? (instance = new ModeloJuego(cliente)) : instance;
    }

    public void actualizarEstado(Mensaje mensaje) {
        ResRegistroJugador res = (ResRegistroJugador) mensaje;
        this.jugadores = res.getJugadores();
        this.jugador = obtenerJugadorPorId(res.getJugadorNuevoID());
        setChanged();
        notifyObservers(new JugadoresActualizados(this.jugadores));
        setChanged();
        notifyObservers(new JugadorDataActualizada(this.jugador));
    }
    
    public void solicitarInicio() {
        if(cliente.isConnected()) {
            cliente.solicitarInicio(jugador);
        }
    }

    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
    }

    public List<FichaDTO> getFichasDTO() {
        return fichasDTO;
    }

    public void setFichasDTO(List<FichaDTO> fichasDTO) {
        this.fichasDTO = fichasDTO;
    }

    public TableroDTO getTableroDTO() {
        return tableroDTO;
    }

    public void setTableroDTO(TableroDTO tableroDTO) {
        this.tableroDTO = tableroDTO;
    }

    public int getIndiceJugadorActual() {
        return indiceJugadorActual;
    }

    public void setIndiceJugadorActual(int indiceJugadorActual) {
        this.indiceJugadorActual = indiceJugadorActual;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public JugadorDTO obtenerJugadorPorId(String id) {
        for (JugadorDTO jugador : jugadores) {
            if (jugador.getId().equals(id)) {
                return jugador;
            }
        }
        return null;
    }
    
    public void notificar(Mensaje message) {
        
        System.out.println("Estoy obteniendo un: " + message.getComando());
        switch (message.getComando()) {
            case "SOLICITUD_ENVIADA":
                setChanged();
                notifyObservers(new MostrarMensaje("Alguien ya solicito iniciar juego"));
                break;
            case "SOLCITUD_EN_CURSO": 
                ResSolicitarInicio res = (ResSolicitarInicio) message;
                setChanged();
                notifyObservers(new MostrarSolicitudInicio(res.getSolicitante().getNombre()));
            default:
                System.out.println("no llego nada :(");
        }

    }

}
