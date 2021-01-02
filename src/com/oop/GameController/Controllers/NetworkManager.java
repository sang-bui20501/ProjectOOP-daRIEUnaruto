package com.oop.GameController.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.oop.GameController.Networking.SocketClient;


public class NetworkManager {
    private ArrayList<Thread> threadList;
    private ServerSocket server;
    private static NetworkManager instance = null;
    
    private SocketClient client;

    public static NetworkManager getInstance(){
        if(instance == null)
            instance = new NetworkManager();
        return instance;
    }
    private NetworkManager(){
        this.threadList = new ArrayList<Thread>();
    }

    public void establishConnection(int port , String ip){
        this.client = new SocketClient(port , ip);
    }

    public String sendActiveHost(){
        return client.sendQuery("host");
    }

    public String getUserList(){
        return client.sendQuery("ls");
    }

	public void initialServer() throws IOException {
        this.server = new ServerSocket(3005);
        System.out.println("Server listening");
        Socket s = null;
        try {
            while ((s = server.accept()) != null) {
                ClientServerThread t = new ClientServerThread(s);
                this.threadList.add(t.getThread());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public void clearServer() {
        for(Thread t : this.threadList)
            t.stop();
        try {
            this.server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
class ClientServerThread implements Runnable{
    private Socket s = null;
    private BufferedReader reader = null;
    private Scanner in = null;
    private PrintWriter out = null;
    private Thread t;
    public ClientServerThread(Socket s) throws IOException {
        this.s = s;
        this.reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.in = new Scanner(s.getInputStream() , "UTF-8");
        this.out = new PrintWriter(s.getOutputStream());
        this.t = new Thread(this);
        t.start();
    }
    public void run() {
        try{
            while(true){

            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public Thread getThread(){
        return this.t;
    }

}