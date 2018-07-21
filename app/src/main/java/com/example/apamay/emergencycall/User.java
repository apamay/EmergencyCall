package com.example.apamay.emergencycall;

/**
 * Created by apamay on 9/6/2018 AD.
 */

public class User {
    private String username;
    private int user_id;

    public User(int user_id, String username){
        this.user_id = user_id;
        this.username = username;

    }

    public String getUsername(){
        return username;
    }

    public int getUser_id(){
        return user_id;
    }


}
