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
    public boolean isExist(User u){
        return userList.contains(u);
    }
    public void removeUser(User u){
        this.userList.remove(u);
    }
    public String getUserList(){
        String result = "";
        for(User u : this.userList){
            result = result + "#" + u.getAddress();
        }
        return result;
    }
    public static ServerManager getInstance(){
        if(instance == null)
            instance = new ServerManager();
        return instance;
    }


}
