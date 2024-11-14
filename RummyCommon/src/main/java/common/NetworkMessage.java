/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author carlo
 */
public class NetworkMessage implements Serializable {
     private String command;
    private Map<String, Object> params;
    private Object payload;

    public NetworkMessage(String command, Map<String, Object> params, Object payload) {
        this.command = command;
        this.params = params;
        this.payload = payload;
    }

    public String getCommand() {
        return command;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public Object getPayload() {
        return payload;
    }
}
