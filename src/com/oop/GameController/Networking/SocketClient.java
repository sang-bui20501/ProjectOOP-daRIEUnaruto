package com.oop.GameController.Networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.ProcessHandle.Info;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

    private Socket client;
    private PrintWriter out;
    private Scanner in;
    public SocketClient(int port, String ip) {
        try {
            this.client = new Socket(ip, port);
            this.out = new PrintWriter(this.client.getOutputStream(), true);
            this.in = new Scanner(this.client.getInputStream() , "UTF-8");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String sendQuery(String query){
        this.out.println(query);
        this.out.flush();
        return this.in.nextLine();
    }
}
