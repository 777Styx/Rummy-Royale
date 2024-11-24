/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author carlo
 */
public class MessageManager {
    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(Mensaje.class, new MessageAdapter())
        .create();
        
    public static String toJson(Mensaje mensaje) {
        return gson.toJson(mensaje, Mensaje.class);
    }
    
    public static Mensaje fromJson(String json) {
        return gson.fromJson(json, Mensaje.class);
    }
}
