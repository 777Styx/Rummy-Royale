package menuMVC;

import actualizaciones.AvatarsActualizados;
import Cliente.Cliente;
import dtos.ColorCustomDTO;
import dtos.JuegoDTO;
import dtos.JugadorDTO;
import dtos.ManejadorColorDTO;
import dtos.TipoFichaDTO;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import mensajes.Mensaje;
import mensajes.ResConfigurarPartida;
import mensajes.ResRegistroJugador;
import mensajes.ResUnirse;

/**
 *
 * @author julli
 */
public class ModeloMenu extends Observable {

    private JuegoDTO juegoDTO = null;
    private boolean registroVisible = false;
    private Cliente cliente;
    private static ModeloMenu instance;
    private List<JugadorDTO> jugadores;

//    private ModeloMenu(Cliente cliente) {
//        this.cliente = cliente;
//        jugadores = new ArrayList<>();
//    }
    private ModeloMenu() {

        jugadores = new ArrayList<>();
    }

    public void notificar(Mensaje message) {

        System.out.println("Estoy obteniendo un: " + message.getComando());

        switch (message.getComando()) {
            case "PARTIDA_CREADA":
                setChanged();
                notifyObservers(message);
                break;
            case "PARTIDA_NO_CREADA":
                setChanged();
                notifyObservers(message);
                break;
            case "JUGADOR_REGISTRADO":
                break;
            case "PARTIDA_CONFIGURADA":
                setChanged();
                notifyObservers(message);
                ResConfigurarPartida res = (ResConfigurarPartida) message;
                setChanged();
                notifyObservers(new AvatarsActualizados(res.getAvatarsDisponibles()));
                break;
            case "JUGADOR_UNIDO":
                setChanged();
                notifyObservers(message);
                ResUnirse res2 = (ResUnirse) message;
                setChanged();
                notifyObservers(new AvatarsActualizados(res2.getAvatarsDisponibles()));
                break;
            case "JUGADOR_NO_UNIDO":
                setChanged();
                notifyObservers(message);
                break;
            default:
                System.out.println("no llego nada :(");
        }

    }

    public static synchronized ModeloMenu getInstance() {
        if (instance == null) {
            instance = new ModeloMenu();
        }
        return instance;
    }

    private void conectar(int puerto) {
        try {
            this.cliente.connectToServer(puerto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearPartida(int puerto) {
        conectar(puerto);
        if (cliente.isConnected()) {
            cliente.crearPartida();
        } else {
            System.out.println("No conectado");
        }
    }

    public void configurarPartida(int comodines, int rango) {
        JuegoDTO juego = new JuegoDTO();
        juego.setNumComodines(comodines);
        juego.setRangoFichas(rango);
        cliente.configurarPartida(juego);

    }

    public void registrarJugador(JugadorDTO jugador) {
        cliente.registrarJugador(jugador);
    }

    public void unirseAPartida(int puerto) {
        conectar(puerto);
        if (cliente.isConnected()) {
            cliente.unirse();
        }
    }

    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
    }

    public void actualizarJugadores(Mensaje mensaje) {
        ResRegistroJugador res = (ResRegistroJugador) mensaje;
        this.jugadores = res.getJugadores();
        setChanged();
        notifyObservers(mensaje);
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
