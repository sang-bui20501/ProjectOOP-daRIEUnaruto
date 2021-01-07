package com.oop.GameController.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.Timer;

import com.oop.Main;
import com.oop.GameController.Networking.SocketClient;
import com.oop.GameController.Player.Player;
import com.oop.GameController.Skill.SkillRender;

public class NetworkManager {
    private ServerSocket server = null;
    private static NetworkManager instance = null;

    private SocketClient client = null;

    private int port;
    private String ip;
    
    public static NetworkManager getInstance() {
        if (instance == null)
            instance = new NetworkManager();
        return instance;
    }

    /**
     * @implNote establish p2p connection.
     */
    public void establishConnection(int port, String ip) {
        this.port = port;
        this.ip = ip;
        
        if(port == 3005) {
            Timer keepAlive = new Timer(100 , new SocketClient(port , ip));
            keepAlive.start();
        }
    }

    public String sendActiveHost() {
        return client.sendQuery("host");
    }

    public String getUserList() {
        if(this.client == null)
            this.client = new SocketClient(port , ip);
        return client.sendQuery("ls");
    }

    public void initialServer() throws IOException {
        this.server = new ServerSocket(3005);
        System.out.println("Server listening");

        Timer timer = new Timer(10000000, Main.getInstance(1));
        timer.start();


        Timer getClient = new Timer(1 , new ActionListener(){

            Socket s = null;    
			@Override
			public void actionPerformed(ActionEvent ee) { 
                try {
                    if (s == null) {    
                        s = server.accept();
                        Timer childTimer = new Timer(70 , new ClientServerThread(s));
                        childTimer.start();
                    }
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
        }
        });
        getClient.start();
    }
    
	public void clearServer() {
        try {
            if(this.server != null)
                this.server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
class ClientServerThread implements ActionListener {
    private Socket s = null;
    public ClientServerThread(Socket s) throws IOException {
        this.s = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get info
        try {
            ObjectInputStream inp = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

            
            
            ArrayList<Player> responsePlayer  = (ArrayList<Player>) inp.readObject();
            ArrayList<ArrayList<SkillRender>> responseSkillList  = (ArrayList<ArrayList<SkillRender>>) inp.readObject();

            ArrayList<Player> player = PlayerManager.List_Player;
            ArrayList<ArrayList<SkillRender>> skillList = SkillManager.List_Skill;
            out.writeObject(player);
            out.writeObject(skillList);
            out.flush();


            
            PlayerManager.List_Player = responsePlayer;
            SkillManager.List_Skill = responseSkillList;

        } catch (Exception e1) { 
            e1.printStackTrace();
        }
        
    }

}