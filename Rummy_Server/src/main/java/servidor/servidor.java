/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julli
 */
public class Servidor {

    private static final int MAX_JUGADORES = 4;
    private final Set<Socket> jugadoresConectados = Collections.synchronizedSet(new HashSet<>());
    private final int puerto;
    private ServerSocket servidorSocket;
    private boolean activo;

    public Servidor(int puerto) throws IOException {
        this.puerto = puerto;
        this.servidorSocket = new ServerSocket(puerto);
        this.activo = true;
    }

    public void iniciar() {
        System.out.println("Servidor iniciado en el puerto " + puerto);

        while (activo) {
            try {
                Socket nuevoJugador = servidorSocket.accept();

                if (jugadoresConectados.size() >= MAX_JUGADORES) {
                    // Rechaza la conexión si ya hay 4 jugadores
                    try (ObjectOutputStream salida = new ObjectOutputStream(nuevoJugador.getOutputStream())) {
                        salida.writeBoolean(false);  // Envía false indicando que el juego está lleno
                        salida.flush();
                    }
                    nuevoJugador.close();
                } else {
                    // Acepta la conexión si hay espacio y crea un nuevo hilo para manejarlo
                    jugadoresConectados.add(nuevoJugador);
                    new Thread(new JugadorHandler(nuevoJugador, this)).start();
                }
            } catch (IOException e) {
                if (activo) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void detener() {
        activo = false;
        try {
            servidorSocket.close();
            System.out.println("Servidor detenido.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<Socket> getJugadoresConectados() {
        return jugadoresConectados;
    }

    public boolean isActivo() {
        return activo;
    }

    public void removerJugador(Socket jugador) {
        jugadoresConectados.remove(jugador);
        System.out.println("Jugador desconectado. Jugadores activos: " + jugadoresConectados.size());
    }
}
