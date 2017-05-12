package com.example.administrator.omg.History;

import android.support.v7.widget.RecyclerView;

import com.example.administrator.omg.MetaData.Order;
import com.example.administrator.omg.MetaData.Valuation;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */

public interface HistoryContract {
    interface View{
        void setAdapter(RecyclerView.Adapter adapter);
    }

    interface Presenter{
        void notifyDataSetChange();

        void setAdapterOnRecyclerView();

        void deleteOrder(long id);

        void addCommment(Valuation valuation);
    }

    interface Model{
        List<Order> getAllOrdersByPage(int page);

        Order getOrderById(long id);

        void addOrder(Order order);

        void deleteOrder(long id);

        void destroyInstance();
    }
}
