package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julli
 */
public class JugadorHandler implements Runnable {

    private final Socket jugadorSocket;
    private final Servidor servidor;

    public JugadorHandler(Socket jugadorSocket, Servidor servidor) {
        this.jugadorSocket = jugadorSocket;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try (ObjectInputStream entrada = new ObjectInputStream(jugadorSocket.getInputStream()); ObjectOutputStream salida = new ObjectOutputStream(jugadorSocket.getOutputStream())) {

            salida.writeBoolean(true);  // Confirma conexión permitida
            salida.flush();

            while (servidor.isActivo()) {
                String accion = (String) entrada.readObject();
                procesarAccion(accion, salida);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Cierra el socket y elimina al jugador de la lista de conectados
            try {
                jugadorSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            servidor.removerJugador(jugadorSocket);
        }
    }

//    private void procesarAccion(String accion, ObjectOutputStream salida) throws IOException {
//        // Interpreta y maneja las acciones del jugador
//        switch (accion) {
//            case "UNIRSE":
//                salida.writeObject("Bienvenido al juego.");
//                break;
//            case "MOVIMIENTO":
//                // Actualiza el estado del juego y notifica a otros jugadores
//                Set<Socket> jugadores = servidor.getJugadoresConectados();
//                for (Socket jugador : jugadores) {
//                    if (jugador != jugadorSocket) {
//                        new ObjectOutputStream(jugador.getOutputStream()).writeObject("Actualización del juego");
//                    }
//                }
//                break;
//            case "FINALIZAR":
//                // Termina el juego y envía mensaje de finalización
//                salida.writeObject("Juego finalizado. Calculando posiciones...");
//                break;
//            default:
//                salida.writeObject("Acción no reconocida.");
//        }
//        salida.flush();
//    }
    private void procesarAccion(String accion, ObjectOutputStream salida) throws IOException {
        switch (accion) {
            case "UNIRSE":
                salida.writeObject("Bienvenido al juego.");
                break;
            case "MOVIMIENTO":
                // Usa el mismo ObjectOutputStream ya creado para enviar actualizaciones a otros jugadores
                Set<Socket> jugadores = servidor.getJugadoresConectados();
                for (Socket jugador : jugadores) {
                    if (jugador != jugadorSocket) {
                        try {
                            ObjectOutputStream otroSalida = new ObjectOutputStream(jugador.getOutputStream());
                            otroSalida.writeObject("Actualización del juego");
                            otroSalida.flush();
                        } catch (IOException e) {
                            System.err.println("Error enviando actualización a otro jugador: " + e.getMessage());
                        }
                    }
                }
                break;
            case "FINALIZAR":
                salida.writeObject("Juego finalizado. Calculando posiciones...");
                break;
            default:
                salida.writeObject("Acción no reconocida.");
        }
        salida.flush();
    }
}
