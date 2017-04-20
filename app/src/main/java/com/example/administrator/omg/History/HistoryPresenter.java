package com.example.administrator.omg.History;

/**
 * Created by Administrator on 2017/4/19.
 */

public class HistoryPresenter {

    private HistoryContract.View view;
    private HistoryContract.Model model;

    private OrderAdapter adapter;

    public HistoryPresenter(HistoryContract.View view,HistoryContract.Model model){
        this.view = view;
        this.model = model;
    }

    void setAdapterOnRecyclerView(){
        adapter = new OrderAdapter(model.getAllOrdersByPage(0));
        view.setAdapter(adapter);
    }
}
