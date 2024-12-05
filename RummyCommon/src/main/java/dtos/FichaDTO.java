/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author carlo
 */
public class FichaDTO {

    private int numero;
    private TipoFichaDTO tipo;
    private boolean comodin;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoFichaDTO getTipo() {
        return tipo;
    }

    public void setTipo(TipoFichaDTO tipo) {
        this.tipo = tipo;
    }

    public boolean isComodin() {
        return comodin;
    }

    public void setComodin(boolean comodin) {
        this.comodin = comodin;
    }

}
