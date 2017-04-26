package com.example.administrator.omg.History;

import com.example.administrator.omg.MetaData.Valuation;

/**
 * Created by Administrator on 2017/4/19.
 */

public class HistoryPresenter implements HistoryContract.Presenter {

    private HistoryContract.View view;
    private HistoryContract.Model model;

    private OrderAdapter adapter;

    private static HistoryPresenter mInstance;

    protected HistoryPresenter(HistoryContract.View view,HistoryContract.Model model){
        this.view = view;
        this.model = model;
    }

    private HistoryPresenter(){

    }

    public void setView(HistoryContract.View view) {
        this.view = view;
    }

    public void setModel(HistoryContract.Model model) {
        this.model = model;
    }

    public static HistoryPresenter getInstance(){
        if(mInstance==null){
            mInstance = new HistoryPresenter();
        }
        return mInstance;

    }




    @Override
    public void setAdapterOnRecyclerView() {
        adapter = new OrderAdapter(model.getAllOrdersByPage(0));
        view.setAdapter(adapter);
    }

    @Override
    public void deleteOrder(long id) {
        model.deleteOrder(id);
    }

    @Override
    public void addCommment(Valuation valuation) {

    }
}
