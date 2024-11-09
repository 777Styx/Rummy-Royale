package menuMVC;

import dto.JuegoDTO;
import entidades.*;

import java.awt.Color;
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
    

    public void mostrarRegistro(boolean visible) {
        this.registroVisible = visible;
        setChanged();
        notifyObservers(visible);
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
        
        if(jugadores.size() < 4) {
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
            for(ManejadorColor mc : jugador.getPreferenciasColor()) {
                System.out.println("Ficha " + mc.getTipoFicha() + " sera color " + mc.getColor().getColor());
            }
        }
    }
    
}



//
//    /**
//     * Se registra un jugador
//     *
//     * @param jugador: jugador que se unira al juego
//     * @return
//     */


    


//
//    public int obtenerCantidadJugadores() {
//        return jugadores.size();
//    }
//
//    @Override
//    public void agregarObservador(IModeloObservador t) {
//        observadores.add(t);
//    }
//
//    /**
//     * Notifica a todos los observadores que la cantidad de jugadores ha
//     * cambiado.
//     */
//    @Override
//    public void notificarObservadores() {
//        for (IModeloObservador observador : observadores) {
//            observador.actulizarCantidadJugadores(obtenerCantidadJugadores());
//        }
//    }
//
//    @Override
//    public void removerObservador(IModeloObservador t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public Jugador crearJugador(String nombre, String avatar, Color color1, Color color2, Color color3, Color color4){
//        // crear instancia de jugador??
//        
//        //validaciones i guess
//        if (nombre == null || nombre.trim().isEmpty()) {
//            throw new IllegalArgumentException("El nombre del jugador no puede estar vacio");
//        }
//        if (avatar == null || avatar.trim().isEmpty()) {
//            throw new IllegalArgumentException("Debes seleccionar un avatar");
//        }
//        if (color1 == null || color2 == null || color3 == null || color4 == null) {
//            throw new IllegalArgumentException("Selecciona todos los colores porfavorcito");
//        }
//        
//        Jugador jugador = new Jugador(nombre, avatar);
//        ManejadorColor manejadorColor = jugador.getManejadorColor();
//        
//        manejadorColor.setColor(TipoFicha.TIPO1, color1);
//        manejadorColor.setColor(TipoFicha.TIPO2, color2);
//        manejadorColor.setColor(TipoFicha.TIPO3, color3);
//        manejadorColor.setColor(TipoFicha.TIPO4, color4);
//        
//        return jugador;
//    }

