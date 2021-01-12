package com.example.ungdungdocbao.Models;

public class User {
    private String name;
    private String email;
    private String avatar;
    public User(){
        this.setName("");
        this.setEmail("");
        this.setAvatar("");
    }
    public User(String name, String email, String avatar){
        this.setName(name);
        this.setEmail(email);
        this.setAvatar(avatar);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
