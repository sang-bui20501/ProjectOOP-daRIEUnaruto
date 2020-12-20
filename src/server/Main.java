package server;

import java.io.IOException;

import server.controllers.Server;

public class Main {
    public static void main(String[] args) {
        Thread s;
        try {
            s = new Server(2005);
            s.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
