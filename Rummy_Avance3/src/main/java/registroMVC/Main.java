/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package registroMVC;

/**
 *
 * @author carlo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ModeloRegistro modelo = new ModeloRegistro();
        ControladorRegistro controlador = ControladorRegistro.getInsatnce();
        ViewRegistro vistaRegistro = new ViewRegistro(controlador);
        controlador.setModelo(modelo);
        controlador.setView(vistaRegistro);
        
        vistaRegistro.setVisible(true);
    }
    
}
