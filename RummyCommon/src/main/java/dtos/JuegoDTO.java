package dtos;

import java.util.List;

/**
 *
 * @author julli
 */
public class JuegoDTO {

    private int rangoFichas;
    private int numComodines;

    public JuegoDTO() {

    }

    public int getRangoFichas() {
        return rangoFichas;
    }

    public void setRangoFichas(int rangoFichas) {
        this.rangoFichas = rangoFichas;
    }

    public int getNumComodines() {
        return numComodines;
    }

    public void setNumComodines(int numComodines) {
        this.numComodines = numComodines;
    }

//    private List<JugadorDTO> jugadoresDTO;
//    private boolean partidaActiva;
//    
//    public JuegoDTO() {
//
//    }
//
//    public JuegoDTO(List<JugadorDTO> jugadores, boolean partidaActiva) {
//        this.jugadoresDTO = jugadores;
//        this.partidaActiva = partidaActiva;
//    }
//    
//    public List<JugadorDTO> getJugadores() {
//        return jugadoresDTO;
//    }
//
//    public void setJugadores(List<JugadorDTO> jugadores) {
//        this.jugadoresDTO = jugadores;
//    }
//
//    public boolean isPartidaActiva() {
//        return partidaActiva;
//    }
//
//    public void setPartidaActiva(boolean partidaActiva) {
//        this.partidaActiva = partidaActiva;
//    }
}
