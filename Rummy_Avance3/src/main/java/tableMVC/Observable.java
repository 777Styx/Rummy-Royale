package tableMVC;

/**
 *
 * @author puerta
 */
public interface Observable<T> {

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
}
