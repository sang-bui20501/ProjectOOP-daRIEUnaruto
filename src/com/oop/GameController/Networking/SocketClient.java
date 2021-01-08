package com.oop.GameController.Networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import com.oop.Main;
import com.oop.GameController.Controllers.PlayerManager;
import com.oop.GameController.Controllers.SkillManager;
import com.oop.GameController.Player.Player;
import com.oop.GameController.Skill.SkillRender;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SocketClient implements ActionListener {

    private Socket client;
    private PrintWriter out;
    private Scanner in;

    public SocketClient(int port, String ip) {
        try {
            this.client = new Socket(ip, port);
            this.out = new PrintWriter(this.client.getOutputStream(), true);
            this.in = new Scanner(this.client.getInputStream(), "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendQuery(String query) {
        this.out.println(query);
        this.out.flush();
        String s = this.in.nextLine();
        System.out.println(s);
        return s;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Get Skill Manager List & Player List via Socket connection
        ArrayList<Player> player = PlayerManager.List_Player;
        ArrayList<ArrayList<SkillRender>> skillList = SkillManager.List_Skill;

        ArrayList<Player> responsePlayer = new ArrayList<Player>(2);
        ArrayList<ArrayList<SkillRender>> responseSkillList = new ArrayList<ArrayList<SkillRender>> (2);

        ObjectOutputStream out;
        ObjectInputStream inp;
        try {
            
            out = new ObjectOutputStream(client.getOutputStream());
            out.writeObject(player);
            out.writeObject(skillList);
            out.flush();
            inp = new ObjectInputStream(client.getInputStream());
            responsePlayer = (ArrayList<Player>) inp.readObject();
            responseSkillList = (ArrayList<ArrayList<SkillRender>>) inp.readObject();
  
            if(responsePlayer.get(1 - Main.getInstance().mainPlayerID + 1).useSkill){
                SkillManager.List_Skill = responseSkillList;
            }
            if(responsePlayer.get(1 - Main.getInstance().mainPlayerID + 1).changeState){        
                PlayerManager.List_Player = responsePlayer;
            }
            if(player.get(Main.getInstance().mainPlayerID - 1).useSkill)
                player.get(Main.getInstance().mainPlayerID - 1).useSkill = false;
                
            if(player.get(Main.getInstance().mainPlayerID -1 ).changeState){
                player.get(Main.getInstance().mainPlayerID -1 ).changeState = false;
            }
            
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        

    }
}
