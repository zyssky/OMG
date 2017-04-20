package com.example.administrator.omg.History;

import android.support.v7.widget.RecyclerView;

import com.example.administrator.omg.MetaData.Order;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */

public interface HistoryContract {
    interface View{
        void setAdapter(RecyclerView.Adapter adapter);
    }

    interface Model{
        List<Order> getAllOrdersByPage(int page);

        void addOrder(Order order);

        void deleteOrder(long id);

        void destroyInstance();
    }
}
