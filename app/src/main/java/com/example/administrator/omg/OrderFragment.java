package com.example.administrator.omg;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;

    private List<Order> mdata;


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

        initOrdersData();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        OrderAdapter adapter = new OrderAdapter(mdata);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10,10,10,10));
    }

    void initOrdersData(){
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
    }
}

