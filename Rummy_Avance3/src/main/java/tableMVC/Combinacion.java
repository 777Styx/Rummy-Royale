package tableMVC;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author puerta
 */
public class Combinacion {

    // Fichas que forman la combinación
    private List<IFicha> fichas;

    /**
     * Inicializa la lista de fichas
     */
    public Combinacion() {
        fichas = new ArrayList<>();
    }

    /**
     * Agreaga una ficha a la combinacion
     *
     * @param ficha: ficha que se agregara a la combinacion
     */
    public void agregarFicha(IFicha ficha) {
        fichas.add(ficha);
    }

    /**
     * Verifica si la combinación es valida.
     *
     * @return true si es válida, false en caso contrario
     */
    public boolean esValida() {
        //Falta logica
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Combinacion{");
        sb.append("fichas=").append(fichas);
        sb.append('}');
        return sb.toString();
    }

}
