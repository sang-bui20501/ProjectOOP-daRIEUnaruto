package com.oop.GameController.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.oop.GameController.Networking.SocketClient;


public class NetworkManager {
    private static NetworkManager instance = null;
    
    private SocketClient client;

    public static NetworkManager getInstace(){
        if(instance == null)
            instance = new NetworkManager();
        return instance;
    }
    private NetworkManager(){}
    public void establishConnection(int port , String ip){
        this.client = new SocketClient(port , ip);
    }
}
