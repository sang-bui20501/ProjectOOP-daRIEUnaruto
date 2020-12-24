package server.controllers;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Server extends Thread {
    private ServerSocket server;
    private ServerManager manager = ServerManager.getInstance();
    
    Scanner scan = new Scanner(System.in);
    public Server(int port) throws IOException {
        server = new ServerSocket(port);
    }

    public void run() {
        Socket s = null;
        try {
            while ((s = server.accept()) != null) {
                new ServerThread(s);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}  
