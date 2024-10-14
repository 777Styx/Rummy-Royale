package registroMVC;

import entidades.ColorFicha;
import entidades.Jugador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author puerta
 */
public class Modelo implements IObservable<IModeloObservador> {

    private ArrayList<Jugador> jugadores;
    private List<IModeloObservador> observadores;

    public Modelo() {
        jugadores = new ArrayList<>();
    }

    /**
     * Se registra un jugador
     *
     * @param jugador: jugador que se unira al juego
     * @return
     */
    public boolean registrarJugador(Jugador jugador) {
        if (jugadores.size() >= 4) {
            System.out.println("Aqui no pana ya ta' lleno");
            return false;
        }

        jugadores.add(jugador);
        return true;
    }

    public int obtenerCantidadJugadores() {
        return jugadores.size();
    }

    @Override
    public void agregarObservador(IModeloObservador t) {
        observadores.add(t);
    }

    /**
     * Notifica a todos los observadores que la cantidad de jugadores ha
     * cambiado.
     */
    @Override
    public void notificarObservadores() {
        for (IModeloObservador observador : observadores) {
            observador.actulizarCantidadJugadores(obtenerCantidadJugadores());
        }
    }

    @Override
    public void removerObservador(IModeloObservador t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
