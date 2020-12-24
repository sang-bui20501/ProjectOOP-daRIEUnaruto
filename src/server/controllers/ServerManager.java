package server.controllers;

import java.util.ArrayList;
import java.util.List;

public class ServerManager {
    private static ServerManager instance = null;
    private List<User> userList;
    private ServerManager (){
        this.userList = new ArrayList<User>();
    }

    public void addUser(User u){
        userList.add(u);
    }
    
    public List<User> getUserList(){
        return this.userList;
    }
    public static ServerManager getInstance(){
        if(instance == null)
            instance = new ServerManager();
        return instance;
    }


}
