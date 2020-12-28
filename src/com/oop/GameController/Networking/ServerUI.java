package com.oop.GameController.Networking;

public class ServerUI {
    private static ServerUI instance = null;
    private ServerUI(){

    }
    public static ServerUI getInstance(){
        if(instance == null)
            instance = new ServerUI();
        return instance;
    }
}
