package com.example.administrator.omg.PlaceOrder;



import com.example.administrator.omg.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */

public class PlaceOrderDatas implements PlaceOrderContract.Model {

    private List<String> dates;
    private List<String> times;
    private List<Integer> counts;

    private final static String Time1 = "07:00-09:30";
    private final static String Time2 = "09:30-12:00";
    private final static String Time3 = "14:00-16:00";
    private final static String Time4 = "16:00-18:00";
    private final static String Time5 = "19:00-22:00";


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

        long today = new Date().getTime();
        long oneDay = 1000*60*60*24;
//        long oneHour = 1000*60*60;
        SimpleDateFormat simpleDateFormatForDay = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat simpleDateFormatForHour = new SimpleDateFormat("HH:mm");

        for (int i = 0; i < 7; i++) {
            dates.add(simpleDateFormatForDay.format(new Date(today+i*oneDay)));
        }


        times.add(Time1);
        times.add(Time2);
        times.add(Time3);
        times.add(Time4);
        times.add(Time5);
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
