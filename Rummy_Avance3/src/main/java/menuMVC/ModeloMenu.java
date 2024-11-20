package menuMVC;

import clienteCarlitos.Cliente;
import common.Command;
import dto.JuegoDTO;
import dto.JugadorDTO;

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

    List<JugadorDTO> jugadores = new ArrayList<>();
    private JuegoDTO juegoDTO = null;
    private boolean registroVisible = false;
    private EstadoJuego estadoJuego;
    private Cliente cliente;
    private final Object clienteLock = new Object(); // prueba

    public enum EstadoJuego {
        DESCONECTADO,
        CONECTADO,
        EN_REGISTRO
    }

    public ModeloMenu() {
        this.estadoJuego = EstadoJuego.DESCONECTADO;
    }

    public void crearConexion() {
        cliente = new Cliente();
        cliente.connectToServer();
    }

    public void crearPartida() {
        if (cliente.isConnected()) {
            cliente.crearPartida();
        } else {
            System.out.println("Not Connected createPartida");
        }
    }

//     public boolean isClienteInicializado() {
//        synchronized (clienteLock) {
//            return cliente != null && cliente.isConnected();
//        }
//    }
//    public void crearConexion(String direccion, int puerto) {
//        System.out.println("Modelo: creando conexion");
//
//        try {
//            new Thread(() -> {
//                try {
//                    Socket socket = new Socket(direccion, puerto);
//                    cliente = new Cliente(socket);
//                    cliente.listenForMessage();
//                    cliente.sendMessage();
//
//                    estadoJuego = EstadoJuego.CONECTADO;
//                    setChanged();
//                    notifyObservers(1);
//
//                } catch (IOException e) {
//                    System.out.println(e);
//                    estadoJuego = EstadoJuego.DESCONECTADO;
//                }
//            }).start();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//     public void crearPartida() {
//        synchronized (clienteLock) {
//            if (cliente != null && cliente.isConnected()) {
//                JuegoDTO juegoDTO = new JuegoDTO();
//                Map<String, Object> map = new HashMap<>();
//                map.put("game", juegoDTO);
//                cliente.sendCommand(Command.CREAR_PARTIDA, map);
//            } else {
//                System.out.println("No se puede crear partida: el cliente no está conectado.");
//            }
//        }
//    }
    public JuegoDTO getJuego() {
        return juegoDTO;
    }

    public void setJuego(JuegoDTO juegoDTO) {
        this.juegoDTO = juegoDTO;
    }

    public EstadoJuego getEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(EstadoJuego estadoJuego) {
        this.estadoJuego = estadoJuego;
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
//public void mostrarRegistro(boolean visible) {
//        this.registroVisible = visible;
//        setChanged();
//        notifyObservers(visible);
//    }
// NO BORRARRR
//public boolean registrarJugador(String nombre, String avatar, Color color1, Color color2, Color color3, Color color4) {
//
//        List<ManejadorColor> manejadoresColor = new ArrayList<>();
//        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO1, new ColorCustom(color1)));
//        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO2, new ColorCustom(color2)));
//        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO3, new ColorCustom(color3)));
//        manejadoresColor.add(new ManejadorColor(TipoFicha.TIPO4, new ColorCustom(color4)));
//
//        Jugador jugador = new Jugador(nombre, avatar, manejadoresColor);
//
//        if (jugadores.size() < 4) {
//            jugadores.add(jugador);
//            return true;
//        } else {
//            System.out.println("Juego lleno");
//            return false;
//        }
//    }
//public void imprimirJugadores() {
//        System.out.println("Jugdaroes en el juego: ");
//        for (Jugador jugador : jugadores) {
//            System.out.println("El jugador " + jugador.getNombre() + " usara la skin de " + jugador.getAvatar());
//            System.out.println("Colores del jugador:");
//            for (ManejadorColor mc : jugador.getPreferenciasColor()) {
//                System.out.println("Ficha " + mc.getTipoFicha() + " sera color " + mc.getColor().getColor());
//            }
//        }
//    }
