package com.example.user.brisbinassignment3;

/**
 * Created by brisbij on 10/26/2015.
 */
public class User {
    private long id;
    private String username;

    public User(){
        this.username = "Default user";
    }

    public User(String username){
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
