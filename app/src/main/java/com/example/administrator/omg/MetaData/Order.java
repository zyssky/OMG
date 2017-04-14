package com.example.administrator.omg.MetaData;

import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/4/12.
 */

public class Order {
    long id;
    String sportType;
    String date;
    String time;
    String desc;
    int count;
    double singlePrice;

    public Order(long id, String sportType, String date, @Nullable String desc, int count, double singlePrice){
        this.count = count;
        this.date = date;
        this.desc = desc;
        this.sportType = sportType;
        this.singlePrice = singlePrice;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(double singlePrice) {
        this.singlePrice = singlePrice;
    }
}

