package server.controllers;

public class User {
    private String ip;
    private int port;
    public User(String ip , int port){
        this.ip = ip;
        this.port = port;
    }

    public int getPort(){
        return this.port;
    }
    public String getAddress(){
        return this.ip;
    }
}
