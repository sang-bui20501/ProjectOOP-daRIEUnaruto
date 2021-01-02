package server.controllers;

public class User {
    private String ip;
    public User(String ip){
        this.ip = ip;
    }
    public String getAddress(){
        return this.ip;
    }
}
