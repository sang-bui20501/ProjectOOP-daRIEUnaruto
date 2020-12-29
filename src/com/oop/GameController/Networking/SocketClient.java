package com.oop.GameController.Networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ProcessHandle.Info;
import java.net.Socket;

public class SocketClient {

    private Socket client;

    public SocketClient(int port, String ip) {
        
        try {
            this.client = new Socket(ip, port);
            this.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
