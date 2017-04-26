package com.example.administrator.omg.PlaceOrder;

import com.example.administrator.omg.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */

public class PlaceOrderDatas implements PlaceOrderContract.Model {

    private List<String> dates;
    private List<String> times;
    private List<Integer> counts;


    private static PlaceOrderDatas mInstance;

    public static PlaceOrderDatas getInstance(){
        if(null == mInstance){
            mInstance = new PlaceOrderDatas();
        }
        return mInstance;
    }

    protected PlaceOrderDatas(){
        dates = new ArrayList<>();
        counts = new ArrayList<>();
        times  = new ArrayList<>();

        dates.add("2017-4-23");

        dates.add("2017-4-23");
        dates.add("2017-4-23");
        dates.add("2017-4-23");
        dates.add("2017-4-23");
        counts.add(1);
        counts.add(2);
        counts.add(3);
        counts.add(4);
        counts.add(5);
        times.add("7:00-9:00");
        times.add("7:00-9:00");
        times.add("7:00-9:00");
        times.add("7:00-9:00");
        times.add("7:00-9:00");
    }

    @Override
    public List<String> getDates() {
        return dates;
    }

    @Override
    public List<Integer> getCounts() {
        return counts;
    }

    @Override
    public List<String> getTimes() {
        return times;
    }
}
