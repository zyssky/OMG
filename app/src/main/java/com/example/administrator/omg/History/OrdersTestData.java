package com.example.administrator.omg.History;

import com.example.administrator.omg.MetaData.Order;

import java.text.Format;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        mdata.add(new Order(new Random().nextLong(),"badminton", date,date,1,65));
        mdata.add(new Order(new Random().nextLong(),"tennis", date,date,1,55));
        mdata.add(new Order(new Random().nextLong(),"basketball", date,date,1,45));
        mdata.add(new Order(new Random().nextLong(),"badminton", date,date,1,65));
        mdata.add(new Order(new Random().nextLong(),"tennis", date,date,1,55));
        mdata.add(new Order(new Random().nextLong(),"basketball", date,date,1,45));
        mdata.add(new Order(new Random().nextLong(),"badminton", date,date,1,65));
        mdata.add(new Order(new Random().nextLong(),"tennis", date,date,1,55));
        mdata.add(new Order(new Random().nextLong(),"basketball", date,date,1,45));
        mdata.add(new Order(new Random().nextLong(),"badminton", date,date,1,65));
        mdata.add(new Order(new Random().nextLong(),"tennis", date,date,1,55));
        mdata.add(new Order(new Random().nextLong(),"basketball", date,date,1,45));
        mdata.add(new Order(new Random().nextLong(),"badminton", date,date,1,65));
        mdata.add(new Order(new Random().nextLong(),"tennis", date,date,1,55));
        mdata.add(new Order(new Random().nextLong(),"basketball", date,date,1,45));
        mdata.add(new Order(new Random().nextLong(),"badminton", date,date,1,65));
        mdata.add(new Order(new Random().nextLong(),"tennis", date,date,1,55));
        mdata.add(new Order(new Random().nextLong(),"basketball", date,date,1,45));
        return mdata;
    }

    @Override
    public Order getOrderById(long id) {
        return null;
    }

    @Override
    public void addOrder(Order order) {

    }

    @Override
    public void deleteOrder(long id) {

    }
}
