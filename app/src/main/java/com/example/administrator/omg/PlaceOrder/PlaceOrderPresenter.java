package com.example.administrator.omg.PlaceOrder;

import android.widget.ExpandableListAdapter;

import com.example.administrator.omg.MetaData.Order;

/**
 * Created by Administrator on 2017/4/22.
 */

public class PlaceOrderPresenter implements PlaceOrderContract.Presenter {

    private ExpandableListAdapter adapter;
    private PlaceOrderContract.Model model;
    private PlaceOrderContract.View view;

    private Order curOrder;

    private static PlaceOrderPresenter mInstance;

    public static PlaceOrderPresenter getInstance(){
        return mInstance;
    }

    public static void initPresenter(PlaceOrderContract.View view,PlaceOrderContract.Model model){
        mInstance = new PlaceOrderPresenter(view,model);

    }

    protected PlaceOrderPresenter(PlaceOrderContract.View view,PlaceOrderContract.Model model){
        this.view = view;
        this.model = model;
        curOrder = new Order();
    }

    @Override
    public Order getCurOrder(){
        return curOrder;
    }

    @Override
    public ExpandableListAdapter getExpandListAdapter() {
        return adapter;
    }

    @Override
    public void tryToAddCount() {
        if(curOrder.getCount()==10)
            return;
        view.addCount();
        curOrder.setCount(curOrder.getCount()+1);
    }

    @Override
    public void tryToDecCount() {
        if(curOrder.getCount()==1)
            return;
        view.decCount();
        curOrder.setCount(curOrder.getCount()-1);
    }

    public void updateDate(int pos){
        String date = model.getDates().get(pos);
        view.setPickDate(date);
        curOrder.setDate(date);
    }

    public void updateTime(int pos){
        String time = model.getTimes().get(pos);
        view.setPickTime(time);
        curOrder.setTime(time);
    }

    public void updateDesc(String desc){
        view.setPickDesc(desc);
        curOrder.setDate(desc);
    }
}
