package menuMVC;

import entidades.Juego;
import java.util.Observable;

/**
 *
 * @author julli
 */
public class ModeloMenu extends Observable {

    private Juego juego = null;

    public void crearJuego() {
        if (juego != null) {
            System.out.println("Ya esta hecho pana");
        } else {
            juego = new Juego();

        }
        System.out.println("modelo");
        setChanged();
        notifyObservers(juego);
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

}





//
//
//public class ModeloRegistro implements IObservable<IModeloObservador> {
//
//    private ArrayList<Jugador> jugadores; // C: yo creo que esto deberia ser la info directamente de la entidad
//    private List<IModeloObservador> observadores;
//    private Juego juego;
//
//    public ModeloRegistro() {
//        jugadores = new ArrayList<>();
//    }
//
//    /**
//     * Se registra un jugador
//     *
//     * @param jugador: jugador que se unira al juego
//     * @return
//     */
//    public boolean registrarJugador(Jugador jugador) {
//        if (!juego.validarAvatarNoUsado(jugador.getAvatar())) {
//            if (jugadores.size() >= 4) {
//                System.out.println("Aqui no pana ya ta' lleno");
//                return false;
//            }
//        }
//        
//        jugadores.add(jugador);
//        return true;
//    }
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
//    
//    
//}

