package com.example.administrator.omg;

/**
 * Created by Administrator on 2017/4/12.
 */

public class Valuation {
    String user;
    String date;
    String content;

    public Valuation(String user,String date,String content){
        this.content = content;
        this.date = date;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
