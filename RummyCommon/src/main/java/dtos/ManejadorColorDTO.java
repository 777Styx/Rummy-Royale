package dtos;

/**
 *
 * @author carlo
 */
public class ManejadorColorDTO {
    private TipoFichaDTO tipoFicha;
    private ColorCustomDTO color;

    public ManejadorColorDTO() {
    }
    
    public ManejadorColorDTO(TipoFichaDTO tipoFicha, ColorCustomDTO color) {
        this.tipoFicha = tipoFicha;
        this.color = color;
    }

    public TipoFichaDTO getTipoFicha() {
        return tipoFicha;
    }

    public void setTipoFicha(TipoFichaDTO tipoFicha) {
        this.tipoFicha = tipoFicha;
    }

    public ColorCustomDTO getColor() {
        return color;
    }

    public void setColor(ColorCustomDTO color) {
        this.color = color;
    }
}
