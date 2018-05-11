package com.example.wer.restclient.clases;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by wer on 9/05/2018.
 */

public class User {
    String username;
    String email;
    String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public static ArrayList<User> obtenerUsuario(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        return gson.fromJson(json, type);
    }

}//Finish user
