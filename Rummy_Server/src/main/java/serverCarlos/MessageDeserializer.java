/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverCarlos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.Command;
import dtos.JugadorDTO;
import java.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import mensajes.Mensaje;
import mensajes.MsgCrearPartida;
import mensajes.MsgRegistroJugador;

/**
 *
 * @author carlo
 */
public class MessageDeserializer {
    private static final Gson gson = new GsonBuilder()
            .serializeNulls()
            .create();

    public static Mensaje deserializeMessage(String jsonMessage) {
        try {
            MessageWrapper wrapper = gson.fromJson(jsonMessage, MessageWrapper.class);
            switch (wrapper.comando) {
                case Command.CREAR_PARTIDA:
                    return new MsgCrearPartida();
                    
                case Command.REGISTRAR_JUGADOR:
                    JugadorDTO jugador = (JugadorDTO) deserializeObject(wrapper.datos);
                    return new MsgRegistroJugador(jugador);
                default:
                    throw new IllegalArgumentException("Comando desconocido: " + wrapper.comando);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object deserializeObject(String base64String) throws IOException, ClassNotFoundException {
        if (base64String == null) return null;
        byte[] data = Base64.getDecoder().decode(base64String);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
}

class MessageWrapper {
    String comando;
    String datos;
}
