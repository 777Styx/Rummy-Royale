package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author julli
 */
public class Mazo {

    private List<IFicha> mazoFichas;
    private Random random;
    
    public Mazo(List<IFicha> numericas, List<IFicha> comodines) {
        mazoFichas = new ArrayList<>();
        mazoFichas.addAll(numericas);  
        mazoFichas.addAll(comodines); 
        random = new Random();
        barajearMazo();
    }
    
    public IFicha tomarFicha() {
        if (mazoFichas.isEmpty()) {
            return null;
        }

        int indiceAleatorio = random.nextInt(mazoFichas.size());
        return mazoFichas.remove(indiceAleatorio);
    }

    public void agregarFicha(IFicha ficha) {
        mazoFichas.add(ficha);
    }
    
    
    public boolean estaVacio() {
        return mazoFichas.isEmpty();
    }

    @Override
    public String toString() {
        return "Mazo{" + "mazoFichas=" + mazoFichas + '}';
    }
    
    public void barajearMazo(){
        Collections.shuffle(mazoFichas, random);
    }

}
