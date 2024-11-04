package servidor;

import java.io.IOException;

/**
 *
 * @author julli
 */
public class MainServidor {

    public static void main(String[] args) {
        int puerto = 3000; // El puerto en el que quieres que escuche el servidor
        try {
            Servidor servidor = new Servidor(puerto);
            servidor.iniciar(); // Inicia el servidor
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
