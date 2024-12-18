package partidaMVC;

import Cliente.Cliente;
import actualizaciones.ActualizarDarTurno;
import actualizaciones.ActualizarQuitarTurno;
import actualizaciones.JugadorDataActualizada;
import actualizaciones.JugadoresActualizados;
import actualizaciones.MostrarMano;
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
import mensajes.ResIniciarPartida;
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

    private ModeloJuego() {

        jugadores = new ArrayList<>();
        fichasDTO = new ArrayList<>();
        tableroDTO = new TableroDTO();
        indiceJugadorActual = 0;
        random = new Random();
    }

    public static ModeloJuego getInstance() {
        return instance == null ? (instance = new ModeloJuego()) : instance;
    }

    public void actualizarJugadores(Mensaje mensaje) {
        ResRegistroJugador res = (ResRegistroJugador) mensaje;
        this.jugadores = res.getJugadores();
        this.jugador = obtenerJugadorPorId(res.getJugadorNuevoID());
        setChanged();
        notifyObservers(new JugadoresActualizados(this.jugadores));
        setChanged();
        notifyObservers(new JugadorDataActualizada(this.jugador));
    }
    
    public void actualizarData(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
        this.jugador = obtenerJugadorPorId(this.jugador.getId());
        setChanged();
        notifyObservers(new JugadoresActualizados(this.jugadores));
        setChanged();
        notifyObservers(new JugadorDataActualizada(this.jugador));
        // luego tmb actualizar tablero
        
    }
    
    public void actualizarMazoJugador() {
        setChanged();
        notifyObservers(new MostrarMano(this.jugador));
    }
    
    public void actualizarJugadorNuevo(Mensaje mensaje) {
        ResRegistroJugador res = (ResRegistroJugador) mensaje;
        this.jugadores = res.getJugadores();
        setChanged();
        notifyObservers(new JugadoresActualizados(this.jugadores));
    }
    
    public void verificarTurno() {
        JugadorDTO jugadorActual = jugadores.get(indiceJugadorActual);
        if (jugadorActual.equals(jugador)) {
            setChanged();
            notifyObservers(new ActualizarDarTurno(jugadorActual));
        } else {
            setChanged();
            notifyObservers(new ActualizarQuitarTurno(jugadorActual));
        }
    }
    
    public void actualizarTablero(TableroDTO tablero) {
        this.tableroDTO = tablero;
    }
    
    public void avanzarTurno() {
        indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
        verificarTurno();
    }

    public void solicitarInicio() {
        if (cliente.isConnected()) {
            cliente.solicitarInicio(jugador);
        }
    }
    
    public void responderSolicitudInicio(boolean res) {
        if(cliente.isConnected()) {
            cliente.responderSolicitudInicio(res, this.jugador);
        }
    }
    
    public void pasarTurno() {
         if(cliente.isConnected()) {
            cliente.pasarTurno();
        }
    }
    
    public void tomarFicha() {
        if(cliente.isConnected()) {
            cliente.tomarFicha(this.jugador);
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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
                ResSolicitarInicio res = (ResSolicitarInicio) message;
                setChanged();
                notifyObservers(new MostrarSolicitudInicio(res.getSolicitante().getNombre()));
                break;
            case "SOLCITUD_EN_CURSO":
                setChanged();
                notifyObservers(new MostrarMensaje("Alguien ya solicito iniciar juego"));
                break;
            case "PARTIDA_INICIADA":
                
                break;
            default:
                System.out.println("no llego nada :(");
        }

    }

}
