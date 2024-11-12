package menuMVC;

import clienteCarlitos.Cliente;
import dto.JuegoDTO;
import entidades.*;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author julli
 */
public class ModeloMenu extends Observable {

    List<Jugador> jugadores = new ArrayList<>();
    private Juego juego = null;
    private boolean registroVisible = false;
    private EstadoJuego estadoJuego;
    private Cliente cliente;

    public enum EstadoJuego {
        DESCONECTADO,
        CONECTADO,
        EN_REGISTRO
    }

    public ModeloMenu() {
        this.estadoJuego = EstadoJuego.DESCONECTADO;
    }

    // se supone que esto no se deberia de usar
    public void mostrarRegistro(boolean visible) {
        this.registroVisible = visible;
        setChanged();
        notifyObservers(visible);
    }

    public void crearConexion(String direccion, int puerto) {
        System.out.println("Modelo: creando conexion");
        try {
            cliente = new Cliente(direccion, puerto);
            System.out.println("algo paso aqui");

            if (cliente.isConnected()) {
                cliente.listenForMessage();
                cliente.sendMessage();
                setChanged();
                notifyObservers(1);
                System.out.println("apparently this didnt work");
            } else {
                System.out.println("No se pudo conectar! Estoy en Modelo");
            }
        } catch(Exception e) {
            System.out.println(e);
        }

    }

    public void crearPartida() {
        JuegoDTO juegoDTO = new JuegoDTO();

//        cliente.sendMessageObject(juegoDTO);
    }

//    public void crearJuego() {
//        if (juego != null) {
//            System.out.println("Ya esta hecho pana");
//        } else {
//            juego = new Juego();
//
//        }
//        System.out.println("modelo");
//        setChanged();
//        notifyObservers(juego);
//    }
    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public boolean registrarJugador(String nombre, String avatar, Color color1, Color color2, Color color3, Color color4) {

        List<ManejadorColor> manejadoresColor = new ArrayList<>();
        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO1, new ColorCustom(color1)));
        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO2, new ColorCustom(color2)));
        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO3, new ColorCustom(color3)));
        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO4, new ColorCustom(color4)));

        Jugador jugador = new Jugador(nombre, avatar, manejadoresColor);

        if (jugadores.size() < 4) {
            jugadores.add(jugador);
            return true;
        } else {
            System.out.println("Juego lleno");
            return false;
        }

    }

    public void imprimirJugadores() {
        System.out.println("Jugdaroes en el juego: ");
        for (Jugador jugador : jugadores) {
            System.out.println("El jugador " + jugador.getNombre() + " usara la skin de " + jugador.getAvatar());
            System.out.println("Colores del jugador:");
            for (ManejadorColor mc : jugador.getPreferenciasColor()) {
                System.out.println("Ficha " + mc.getTipoFicha() + " sera color " + mc.getColor().getColor());
            }
        }
    }

    public EstadoJuego getEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(EstadoJuego estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

}
