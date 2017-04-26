package com.example.administrator.omg.PlaceOrder;

import android.widget.ExpandableListAdapter;

/**
 * Created by Administrator on 2017/4/22.
 */

public class PlaceOrderPresenter implements PlaceOrderContract.Presenter {

    private ExpandableListAdapter adapter;
    private PlaceOrderContract.Model model;
    private PlaceOrderContract.View view;

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
    }

    @Override
    public ExpandableListAdapter getExpandListAdapter() {
        return adapter;
    }

    public void updateDate(int pos){
        view.setPickDate(model.getDates().get(pos));
    }

    public void updateCount(int pos){
        view.setPickCount(model.getCounts().get(pos));
    }

    public void updateTime(int pos){
        view.setPickTime(model.getTimes().get(pos));
    }

    public void updateDesc(String desc){
        view.setPickDesc(desc);
    }
}
