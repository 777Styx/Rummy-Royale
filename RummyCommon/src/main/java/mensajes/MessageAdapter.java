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
                // requestsss
                case "REGISTRAR_JUGADOR" -> ReqRegistroJugador.class;
                case "CREAR_PARTIDA" -> ReqCrearPartida.class;
                case "CONFIGURAR_PARTIDA" -> ReqConfigurarPartida.class;
                 // responses
                case "PARTIDA_CREADA", "PARTIDA_NO_CREADA" -> ResCrearPartida.class; 
                case "PARTIDA_CONFIGURADA" -> ResConfigurarPartida.class;
                case "JUGADOR_REGISTRADO" -> ResRegistroJugador.class;
                 
                default -> throw new JsonParseException("Comando desconocido: " + comando);
            };
            
            return context.deserialize(json, messageClass);
        } catch (Exception e) {
            throw new JsonParseException("Error deserializando mensaje", e);
        }
    }
}
