package server;

import java.io.IOException; 

import server.controllers.Server;

public class Main {
    public static void main(String[] args) {
        try {
            (new Server(2005)).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
