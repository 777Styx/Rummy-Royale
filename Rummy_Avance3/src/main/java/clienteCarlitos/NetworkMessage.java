/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteCarlitos;

/**
 *
 * @author carlo
 */
public class NetworkMessage {
    private String protocoloRummy;
    private Object payload;
    
    public static final String CREAR_PARTIDA = "CREAR_PARTIDA";
    public static final String UNIRSE_PARTIDA = "UNIRSE_PARTIDA";

    public NetworkMessage(String protocoloRummy, Object payload) {
        this.protocoloRummy = protocoloRummy;
        this.payload = payload;
    }

    public String getProtocoloRummy() {
        return protocoloRummy;
    }

    public void setProtocoloRummy(String protocoloRummy) {
        this.protocoloRummy = protocoloRummy;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
    
    
}
