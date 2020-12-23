package com.oop.GameController.Networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ProcessHandle.Info;
import java.net.Socket;

public class SocketClient {
    private static SocketClient socketClient = null;

    private Socket client;

    private SocketClient(int port, String ip) {
        
        try {
            this.client = new Socket(ip, port);
            this.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run(){
        try{
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while(this.client.isConnected()){
                System.out.println("Server says " + inFromServer.readLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static SocketClient getClient(){
        if(socketClient == null) 
            socketClient = new SocketClient(2005 , "localhost");
        return socketClient;
    }
}
