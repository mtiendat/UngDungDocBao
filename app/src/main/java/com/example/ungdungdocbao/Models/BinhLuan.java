package com.example.ungdungdocbao.Models;

public class BinhLuan {
    private String nameUser;
    private String content;
    private String avatar;
    public BinhLuan(String nameUser, String content, String avatar){
        this.nameUser = nameUser;
        this.content = content;
        this.avatar=avatar;
    }
    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
