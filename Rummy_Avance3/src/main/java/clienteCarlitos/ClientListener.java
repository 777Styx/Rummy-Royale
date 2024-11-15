/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package clienteCarlitos;

import common.NetworkMessage;

/**
 *
 * @author carlo
 */
public interface ClientListener {
    void onMessageReceived(String message);
    void onNetworkMessageReceived(NetworkMessage message);
    void onConnectionStateChanged(boolean connected);
}
