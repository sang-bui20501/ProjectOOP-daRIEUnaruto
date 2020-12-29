package server.controllers;

import java.util.ArrayList;
import java.util.List;

public class ServerManager {
    private static ServerManager instance = null;
    private ArrayList<User> userList;
    private ServerManager (){
        this.userList = new ArrayList<User>();
    }

    public void addUser(User u){
        userList.add(u);
    }
    public boolean isExist(User u){
        System.out.println(u.getAddress());
        for(User user : this.userList){
            if(user.getAddress().equals(u.getAddress()))
                return true;
        }
        return false;
    }
    public void removeUser(User u){
        for(User user : this.userList){
            if(user.getAddress().equals(u.getAddress())){
                this.userList.remove(user);
                return;
            }
        }
    }
    public String getUserList(){
        String result = "";
        for(User u : this.userList){
            result = result + "#" + u.getAddress();
        }
        return result;
    }
    public int getUserListLength(){
        return this.userList.size();
    }
    public static ServerManager getInstance(){
        if(instance == null)
            instance = new ServerManager();
        return instance;
    }


}
