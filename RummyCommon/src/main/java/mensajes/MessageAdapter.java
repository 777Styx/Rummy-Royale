/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 *
 * @author carlo
 */
public class MessageAdapter implements JsonSerializer<Mensaje>, JsonDeserializer<Mensaje> {
    @Override
    public JsonElement serialize(Mensaje mensaje, Type type, JsonSerializationContext context) {
        return context.serialize(mensaje);
    }
    
    @Override
    public Mensaje deserialize(JsonElement json, Type type, JsonDeserializationContext context) 
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String comando = jsonObject.get("comando").getAsString();
        
        try {
            Class<? extends Mensaje> messageClass = switch (comando) {
                case "REGISTRO_JUGADOR" -> MsgRegistroJugador.class;
                case "CREAR_PARTIDA" -> MsgCrearPartida.class;
                // Agregar mas aki
                default -> throw new JsonParseException("Comando desconocido: " + comando);
            };
            
            return context.deserialize(json, messageClass);
        } catch (Exception e) {
            throw new JsonParseException("Error deserializando mensaje", e);
        }
    }
}
