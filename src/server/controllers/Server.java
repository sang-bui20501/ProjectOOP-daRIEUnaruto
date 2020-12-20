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
                
                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                
                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
                + "\nGoodbye!");
                server.close();
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
