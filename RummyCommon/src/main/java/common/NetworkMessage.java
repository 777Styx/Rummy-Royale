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
    private Command command;
    private Map<String, Object> params;

    public NetworkMessage(Command command, Map<String, Object> params) {
        this.command = command;
        this.params = params;
    }

    public Command getCommand() {
        return command;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}

