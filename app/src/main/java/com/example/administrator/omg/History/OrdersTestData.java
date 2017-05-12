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
        String time = "07:00-09:30";
//        mdata.add(new Order(new Random().nextLong(),"羽毛球", date,time,1,65));
//        mdata.add(new Order(new Random().nextLong(),"网球", date,time,1,55));
//        mdata.add(new Order(new Random().nextLong(),"篮球", date,time,1,45));
//        mdata.add(new Order(new Random().nextLong(),"羽毛球", date,time,1,65));
//        mdata.add(new Order(new Random().nextLong(),"游泳", date,time,1,55));
//        mdata.add(new Order(new Random().nextLong(),"篮球", date,time,1,45));
//        mdata.add(new Order(new Random().nextLong(),"羽毛球", date,time,1,65));
//        mdata.add(new Order(new Random().nextLong(),"乒乓球", date,time,1,55));
//        mdata.add(new Order(new Random().nextLong(),"足球", date,time,1,45));
//        mdata.add(new Order(new Random().nextLong(),"羽毛球", date,time,1,65));
        return mdata;
    }

    @Override
    public Order getOrderById(long id) {
        return null;
    }

    @Override
    public void addOrder(Order order) {
        mdata.add(order);
    }

    @Override
    public void deleteOrder(long id) {
        int target = -1;
        for (int i = 0; i < mdata.size(); i++) {
            if(id == mdata.get(i).getId()){
                target = i;
                break;
            }
        }
        if(target>=0){
            mdata.remove(target);
        }
    }
}
