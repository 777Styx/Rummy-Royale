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
        Modelo modelo = new Modelo();
        Controlador controlador = Controlador.getInsatnce();
        View vistaRegistro = new View(controlador);
        controlador.setModelo(modelo);
        controlador.setView(vistaRegistro);
        
        vistaRegistro.setVisible(true);
    }
    
}
