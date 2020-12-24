package server.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable {
    private Socket s = null;
    private BufferedReader reader = null;
    private Scanner in = null;

    private PrintWriter out = null;

    public ServerThread(Socket s) throws IOException {
        System.out.println(s.getInetAddress());
        System.out.println(s.getPort());
        
        this.s = s;
        this.reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.in = new Scanner(s.getInputStream() , "UTF-8");
        this.out = new PrintWriter(s.getOutputStream());
        new Thread(this).start();
    }

	@Override
	public void run() {
		// try{
        //     while(true){

        //     }
        // }catch(IOException e){
        //     e.printStackTrace();
        // }
	}
}
