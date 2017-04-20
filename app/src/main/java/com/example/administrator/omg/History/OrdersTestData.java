package com.example.administrator.omg.History;

import com.example.administrator.omg.MetaData.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/4/19.
 */

public class OrdersTestData implements HistoryContract.Model {

    private List<Order> mdata;

    private static HistoryContract.Model mInstance;

    protected OrdersTestData(){

    }

    public static HistoryContract.Model getInstance(){
        if(mInstance==null)
            mInstance = new OrdersTestData();
        return mInstance;
    }

    @Override
    public void destroyInstance(){
        mInstance = null;
    }

    @Override
    public List<Order> getAllOrdersByPage(int page) {

        mdata = new ArrayList<>();
        mdata.add(new Order(new Random().nextLong(),"badminton", new Date().toString(),null,1,65));
        mdata.add(new Order(new Random().nextLong(),"tennis", new Date().toString(),null,1,55));
        mdata.add(new Order(new Random().nextLong(),"basketball", new Date().toString(),null,1,45));
        mdata.add(new Order(new Random().nextLong(),"badminton", new Date().toString(),null,1,65));
        mdata.add(new Order(new Random().nextLong(),"tennis", new Date().toString(),null,1,55));
        mdata.add(new Order(new Random().nextLong(),"basketball", new Date().toString(),null,1,45));
        mdata.add(new Order(new Random().nextLong(),"badminton", new Date().toString(),null,1,65));
        mdata.add(new Order(new Random().nextLong(),"tennis", new Date().toString(),null,1,55));
        mdata.add(new Order(new Random().nextLong(),"basketball", new Date().toString(),null,1,45));
        mdata.add(new Order(new Random().nextLong(),"badminton", new Date().toString(),null,1,65));
        mdata.add(new Order(new Random().nextLong(),"tennis", new Date().toString(),null,1,55));
        mdata.add(new Order(new Random().nextLong(),"basketball", new Date().toString(),null,1,45));
        mdata.add(new Order(new Random().nextLong(),"badminton", new Date().toString(),null,1,65));
        mdata.add(new Order(new Random().nextLong(),"tennis", new Date().toString(),null,1,55));
        mdata.add(new Order(new Random().nextLong(),"basketball", new Date().toString(),null,1,45));
        mdata.add(new Order(new Random().nextLong(),"badminton", new Date().toString(),null,1,65));
        mdata.add(new Order(new Random().nextLong(),"tennis", new Date().toString(),null,1,55));
        mdata.add(new Order(new Random().nextLong(),"basketball", new Date().toString(),null,1,45));
        return mdata;
    }

    @Override
    public void addOrder(Order order) {

    }

    @Override
    public void deleteOrder(long id) {

    }
}
