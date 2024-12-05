package entidades;

import dtos.TipoFichaDTO;

/**
 *
 * @author julli
 */
public class FichaComodin implements IFicha {

    @Override
    public int getNumero() {
        return 0;
    }

    @Override
    public TipoFichaDTO getTipo() {
        return TipoFichaDTO.TIPO1;
    }

    @Override
    public boolean isComodin() {
        return true;
    }
        
    
}
