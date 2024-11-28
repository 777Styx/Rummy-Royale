package menuMVC;

import clienteCarlitos.Cliente;
import dtos.ColorCustomDTO;
import dtos.JuegoDTO;
import dtos.JugadorDTO;
import dtos.ManejadorColorDTO;
import dtos.TipoFichaDTO;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author julli
 */
public class ModeloMenu extends Observable {

    private JuegoDTO juegoDTO = null;
    private boolean registroVisible = false;
    private Cliente cliente;
    private static ModeloMenu instance;

    private ModeloMenu() {

    }

    public void notificar(String message) {
        switch (message) {
            case "PARTIDA_CREADA":
                System.out.println("Se creo una partida, soy chileno");
                setChanged();
                notifyObservers(message);
                break;
            case "PARTIDA_NO_CREADA":
                System.out.println("Ya estaba creada una partida");
                break;

            case "JUGADOR_REGISTRADO":
                System.out.println("Se crea alv");
            default:
                System.out.println("no llego nada :(");
        }

    }

    public static ModeloMenu getInstance() {
        return instance == null ? (instance = new ModeloMenu()) : instance;
    }

    private void conectar(int puerto) {
        cliente = new Cliente();
        try {
            cliente.connectToServer(puerto);
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

    public void configurarPartida(int comodines, int rango){
        JuegoDTO juego = new JuegoDTO();
        juego.setNumComodines(comodines);
        juego.setRangoFichas(rango);
        cliente.configurarPartida(juego);
        
    }
    
    public void registrarJugador(String nombre, String avatar, Color color1, Color color2, Color color3, Color color4) {

        List<ManejadorColorDTO> manejadoresColor = new ArrayList<>();
        manejadoresColor.add(new ManejadorColorDTO(TipoFichaDTO.TIPO1, new ColorCustomDTO(color1.getRGB())));
        manejadoresColor.add(new ManejadorColorDTO(TipoFichaDTO.TIPO2, new ColorCustomDTO(color2.getRGB())));
        manejadoresColor.add(new ManejadorColorDTO(TipoFichaDTO.TIPO3, new ColorCustomDTO(color3.getRGB())));
        manejadoresColor.add(new ManejadorColorDTO(TipoFichaDTO.TIPO4, new ColorCustomDTO(color4.getRGB())));
        JugadorDTO jugador = new JugadorDTO();

        jugador.setNombre(nombre);
        jugador.setAvatar(avatar);
        jugador.setPreferenciasColor(manejadoresColor);

        cliente.registrarJugador(jugador);

    }

    public void unirseAPartida(int puerto) {
        conectar(puerto);

        if (cliente.isConnected()) {
            cliente.unirse();
        }
    }

    public void seRegistroJugador() {
        setChanged();
        notifyObservers("JUGADOR_REGISTRADO");
    }
}
