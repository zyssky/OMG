package com.example.administrator.omg.PlaceOrder;

import android.widget.ExpandableListAdapter;

import com.example.administrator.omg.MetaData.Order;

import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */

public interface PlaceOrderContract {
    interface View{
        void setPickDate(String date);
        void setPickTime(String time);
        void addCount();
        void decCount();
        void setPickDesc(String desc);
    }

    interface Presenter{
        ExpandableListAdapter getExpandListAdapter();

        void tryToAddCount();
        void tryToDecCount();

        Order getCurOrder();
    }

    interface Model{
        List<String> getDates();

        List<Integer> getCounts();

        List<String> getTimes();
    }
}
