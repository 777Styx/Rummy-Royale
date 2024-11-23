/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteCarlitos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import mensajes.Mensaje;
import mensajes.MsgConfigurarPartida;
import mensajes.MsgRegistroJugador;

/**
 *
 * @author carlo
 */
public class MessageSerializer {

    private static final Gson gson = new GsonBuilder()
            .serializeNulls()
            .create();

    public static String serializableCommanWithData(Mensaje mensaje) {
        try {
            MessageWrapper wrapper = new MessageWrapper();
            wrapper.comando = mensaje.getComando();
            if (mensaje instanceof MsgRegistroJugador) {
                wrapper.datos = serializeObject(((MsgRegistroJugador) mensaje).getJugador());
            } else if (mensaje instanceof MsgConfigurarPartida) {
                wrapper.datos = serializeObject(((MsgConfigurarPartida) mensaje).getJuego());
            }

            return gson.toJson(wrapper);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String serializeObject(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}

class MessageWrapper {

    String comando;
    String datos;
}
