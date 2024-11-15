package menuMVC;

import clienteCarlitos.Cliente;
import common.Command;
import dto.JuegoDTO;
import entidades.*;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Scanner;
import javax.swing.SwingUtilities;

/**
 *
 * @author julli
 */
public class ModeloMenu extends Observable {

    List<Jugador> jugadores = new ArrayList<>();
    private Juego juego = null;
    private boolean registroVisible = false;
    private EstadoJuego estadoJuego;
    private boolean clienteInicializado = false;
    Cliente cliente;

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

    public boolean isClienteInicializado() {
        return clienteInicializado;
    }

    public void crearConexion(String direccion, int puerto) {
        System.out.println("Modelo: creando conexion");

        try {
            new Thread(() -> {
                try {
                    Socket socket = new Socket(direccion, puerto);
                    cliente = new Cliente(socket);
                    cliente.listenForMessage();
                    cliente.sendMessage();

                    setChanged();
                    notifyObservers(1);

                } catch (IOException e) {
                    System.out.println(e);
                }
            }).start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    public void crearPartida() {
//        JuegoDTO juegoDTO = new JuegoDTO();
//        Map<String, Object> map = new HashMap<>();
//        map.put("game", juegoDTO);
//        cliente.sendCommand(Command.CREAR_PARTIDA, map);
//
////        cliente.sendMessageObject(juegoDTO);
//    }
    public void crearPartida() {
        if (isClienteInicializado()) {
            // Ahora el cliente está listo para ser usado
            JuegoDTO juegoDTO = new JuegoDTO();
            Map<String, Object> map = new HashMap<>();
            map.put("game", juegoDTO);
            cliente.sendCommand(Command.CREAR_PARTIDA, map);
        } else {
            System.out.println("El cliente aún no está listo. Esperando...");
            // Podrías agregar lógica aquí para manejar la espera (como una notificación al usuario)
        }
    }

    // prueba de caros 12nov
//    public void crearJuego(int rangoFichas, int comodines) {
//        try {
//            JuegoDTO juegoDTO = new JuegoDTO();
//            juegoDTO.setNumComodines(comodines);
//            juegoDTO.setRangoFichas(rangoFichas);
//            NetworkMessage message = new NetworkMessage(NetworkMessage.CREAR_PARTIDA, juegoDTO);
//            cliente.sendNetworkMessage(message);
//        } catch(Exception e) {
//            System.out.println(e);
//        }
//    }
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

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public EstadoJuego getEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(EstadoJuego estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

}
