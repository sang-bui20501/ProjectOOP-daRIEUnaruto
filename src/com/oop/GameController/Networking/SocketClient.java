package com.oop.GameController.Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
    private static SocketClient socketClient = null;

    private Socket client;

    private SocketClient(int port, String ip) {
        
        try {
            this.client = new Socket(ip, port);
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
    
            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
             
            System.out.println("Server says " + in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SocketClient getClient(){
        if(socketClient == null) 
            socketClient = new SocketClient(2005 , "localhost");
        return socketClient;
    }
}
