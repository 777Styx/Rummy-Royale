package entidades;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author julli
 */
public class Mazo {

    private ArrayList<IFicha> mazoFichas;
    private Random random;

    public Mazo() {
        mazoFichas = new ArrayList<>();
        random = new Random();
    }

    /**
     * Toma una ficha al azar del mazo y la elimina de la lista.
     *
     * @return La ficha seleccionada al azar, o null si el mazo esta vacío.
     */
    public IFicha tomarFicha() {
        if (mazoFichas.isEmpty()) {
            System.out.println("No hay más fichas en el mazo.");
            return null;
        }

        int indiceAleatorio = random.nextInt(mazoFichas.size());

        return mazoFichas.remove(indiceAleatorio);
    }

    /**
     * Agrega una ficha al mazo.
     *
     * @param ficha La ficha a agregar al mazo.
     */
    public void agregarFicha(IFicha ficha) {
        mazoFichas.add(ficha);
    }

    /**
     * Verifica si el mazo esta vacío.
     *
     * @return true si el mazo esta vacío, false de lo contrario.
     */
    public boolean estaVacio() {
        return mazoFichas.isEmpty();
    }

}
