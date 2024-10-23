/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package registroMVC;

import menuMVC.VistaRegistro;
import menuMVC.ControladorRegistro;
import menuMVC.ModeloRegistro;

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
        //   ControladorRegistro controlador = ControladorRegistro.getInsatnce();
        ControladorRegistro controlador = new ControladorRegistro(modelo);
        
        VistaRegistro vistaRegistro = new VistaRegistro(controlador);
        controlador.setModelo(modelo);
        // controlador.setView(vistaRegistro);
    
        vistaRegistro.setVisible(true);
    }

}
