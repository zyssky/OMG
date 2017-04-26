package com.example.administrator.omg.MetaData;

/**
 * Created by Administrator on 2017/4/12.
 */

public class Valuation {
    String user;
    String date;
    String content;

    long orderId;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Valuation(String user, String date, String content){
        this.content = content;
        this.date = date;
        this.user = user;
    }

    public Valuation(long id,String content){
        this.orderId = id;
        this.content  = content;
    }

    public Valuation(){

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
