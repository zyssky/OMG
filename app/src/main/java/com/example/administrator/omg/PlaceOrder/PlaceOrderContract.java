package com.example.administrator.omg.PlaceOrder;

import android.widget.ExpandableListAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */

public interface PlaceOrderContract {
    interface View{
        void setPickDate(String date);
        void setPickTime(String time);
        void setPickCount(Integer count);
        void setPickDesc(String desc);
    }

    interface Presenter{
        ExpandableListAdapter getExpandListAdapter();
    }

    interface Model{
        List<String> getDates();

        List<Integer> getCounts();

        List<String> getTimes();
    }
}
