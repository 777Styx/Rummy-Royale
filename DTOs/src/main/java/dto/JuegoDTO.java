package dto;

import java.util.List;

/**
 *
 * @author julli
 */
public class JuegoDTO {

    private List<JugadorDTO> jugadoresDTO;
    private boolean partidaActiva;

    // Constructor
    public JuegoDTO() {

    }

    public JuegoDTO(List<JugadorDTO> jugadores, boolean partidaActiva) {
        this.jugadoresDTO = jugadores;
        this.partidaActiva = partidaActiva;
    }

    // Getters y Setters
    public List<JugadorDTO> getJugadores() {
        return jugadoresDTO;
    }

    public void setJugadores(List<JugadorDTO> jugadores) {
        this.jugadoresDTO = jugadores;
    }

    public boolean isPartidaActiva() {
        return partidaActiva;
    }

    public void setPartidaActiva(boolean partidaActiva) {
        this.partidaActiva = partidaActiva;
    }

}
