package com.oop.GameController.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.oop.GameController.Networking.User;

public class NetworkManager {
    private ArrayList<User> users;

    private NetworkManager(){

    }

    public List<User> getUserList(){
        return this.users;
    }
}
