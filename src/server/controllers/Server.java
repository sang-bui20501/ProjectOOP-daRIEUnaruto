package server.controllers;

import java.net.*;
import java.io.*;

public class Server extends Thread{
    private ServerSocket socket;
    public Server (int port) throws IOException{
        socket = new ServerSocket(port);
    }   
    public void run(){
        while(true){
            try{
                System.out.println("Waiting for client on port " + 
                socket.getLocalPort() + "...");
                Socket server = socket.accept();
                
                PrintStream output = new PrintStream(server.getOutputStream());
                while(server.isConnected()){
                    output.println("asdfasdfasdf");
                }
            }catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}  
