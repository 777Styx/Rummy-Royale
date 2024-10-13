package tableMVC;

import java.util.List;

/**
 *
 * @author julli
 */
import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private List<Combinacion> combinaciones; // Lista para almacenar combinaciones

    public Tablero() {
        combinaciones = new ArrayList<>(); // Inicializa la lista de combinaciones
    }

    /**
     * Agrega una nueva combinación al tablero.
     *
     * @param combinacion La combinación a agregar
     */
    public void agregarCombinacion(Combinacion combinacion) {
        if (esCombinacionValida(combinacion)) {
            combinaciones.add(combinacion);
        } else {
            System.out.println("Combinación no válida: " + combinacion);
        }
    }

    /**
     * Verifica si la combinación es válida.
     *
     * @param combinacion La combinacion a verificar
     * @return true si es valida, false en caso contrario
     */
    private boolean esCombinacionValida(Combinacion combinacion) {
        // Lógica para verificar si la combinación es válida
        // Por ejemplo, comprobar si es un grupo o una secuencia válida
        return combinacion.esValida();
    }

    /**
     * Obtiene todas las combinaciones en el tablero.
     *
     * @return Lista de combinaciones
     */
    public List<Combinacion> obtenerCombinaciones() {
        return combinaciones;
    }

}
