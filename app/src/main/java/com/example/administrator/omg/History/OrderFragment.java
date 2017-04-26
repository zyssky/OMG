package com.example.administrator.omg.History;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.MetaData.Order;
import com.example.administrator.omg.R;
import com.example.administrator.omg.util.BaseRecyclerAdapter;
import com.example.administrator.omg.util.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment implements HistoryContract.View {

    private View rootView;
    private RecyclerView recyclerView;
    private Context context;

    private HistoryContract.Presenter presenter;

    private BaseRecyclerAdapter.OnItemClickListener commentListener;
    private BaseRecyclerAdapter.OnItemClickListener deleteListener;


    public OrderFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HistoryPresenter tmpPresenter = HistoryPresenter.getInstance();
        tmpPresenter.setView(this);
        tmpPresenter.setModel(OrdersTestData.getInstance());
        presenter = tmpPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_order, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.orders);



        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.setAdapterOnRecyclerView();
        recyclerView.addItemDecoration(new SpaceItemDecoration(10,10,10,10));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        if(adapter instanceof OrderAdapter){
            OrderAdapter orderAdapter = (OrderAdapter) adapter;
            if(commentListener==null){
                commentListener = new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object data) {
                        if(data instanceof Order) {
                            Order order = (Order) data;
                            Intent intent = new Intent(context, GiveCommentActivity.class);
                            intent.putExtra(AppContants.ORDER_ID, order.getId());
                            context.startActivity(intent);
                        }
                    }
                };
            }
            if(deleteListener == null){
                deleteListener = new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object data) {
                        if(data instanceof Order) {
                            Order order = (Order) data;
                            presenter.deleteOrder(order.getId());
                        }
                    }
                };
            }

            orderAdapter.setmCommentListener(commentListener);
            orderAdapter.setmDeleteListener(deleteListener);
        }
        recyclerView.setAdapter(adapter);
    }
}

