package com.example.administrator.omg.MetaData;

import android.support.annotation.Nullable;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/4/12.
 */

public class Order {
    long id;
    String sportType;
    String date;
    String time;
    String desc;
    int count = 1;
    double singlePrice;

    public void setTotal(double total) {
        DecimalFormat decimalFormat = new DecimalFormat("###.00");
        total = Double.parseDouble(decimalFormat.format(total));
        this.total = total;
    }

    double total;

    public Order(long id, String sportType, String date,String time ,int count, double singlePrice,double total){
        this.count = count;
        this.date = date;
        this.time = time;
        this.sportType = sportType;
        this.singlePrice = singlePrice;
        this.id = id;
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("于");
        sb.append(date);
        sb.append("，定购");
        sb.append(sportType);
        sb.append("球场");
        sb.append(count);
        sb.append("个");
        return  sb.toString();

    }

    public Order(){}

    public boolean isOk(){
        setTotal(singlePrice*count);
        return sportType!=null && date!=null && time!=null && count>=1 && singlePrice>0;
    }

    public double getTotal(){
        DecimalFormat decimalFormat = new DecimalFormat("###.00");
        total = Double.parseDouble(decimalFormat.format(total));
        return total;
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

