/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteCarlitos;

import common.NetworkMessage;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author carlo
 */
public class MessageQueue {
    // Hacer la instancia volatile para asegurar visibilidad entre hilos
    private static volatile MessageQueue instance;
    private final BlockingQueue<NetworkMessage> messageQueue;

    private MessageQueue() {
        messageQueue = new LinkedBlockingQueue<>();
    }

    // Implementación thread-safe del Singleton usando double-checked locking
    public static MessageQueue getInstance() {
        MessageQueue result = instance;
        if (result == null) {
            synchronized (MessageQueue.class) {
                result = instance;
                if (result == null) {
                    instance = result = new MessageQueue();
                }
            }
        }
        return instance;
    }

    public void addMessage(NetworkMessage message) {
        try {
            messageQueue.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public NetworkMessage takeMessage() throws InterruptedException {
        return messageQueue.take();
    }
}