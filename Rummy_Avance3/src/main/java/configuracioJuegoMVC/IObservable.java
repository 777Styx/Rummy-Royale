package configuracioJuegoMVC;

import tableMVC.*;

/**
 *
 * @author puerta
 */
public interface IObservable<T> {

    /**
     * Agrega observadores al observable
     *
     * @param t: observador a agregar
     */
    public void agregarObservador(T t);

    /**
     * Remueve observador
     *
     * @param t: observador a remover
     */
    public void removerObservador(T t);

    /**
     * Notifica a todos los observadores sobre un cambio.
     */
    void notificarObservadores();
}
