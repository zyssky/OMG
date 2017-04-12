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
import java.util.List;

public class UserValuationsFragment extends Fragment {


    private RecyclerView recyclerView;

    private List<Valuation> datas;

    private View rootView;

    public UserValuationsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static UserValuationsFragment newInstance() {
        UserValuationsFragment fragment = new UserValuationsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user_comments, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.valuations_recyView);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();

        initRecyclerView();
    }

    private void initDatas() {
        datas = new ArrayList<>();
        datas.add(new Valuation("user","date","content"));
        datas.add(new Valuation("user","date","content"));
        datas.add(new Valuation("user","date","content"));
        datas.add(new Valuation("user","date","content"));
        datas.add(new Valuation("user","date","content"));
        datas.add(new Valuation("user","date","content"));
        datas.add(new Valuation("user","date","content"));
        datas.add(new Valuation("user","date","content"));
        datas.add(new Valuation("user","date","content"));
        datas.add(new Valuation("user","date","content"));
        datas.add(new Valuation("user","date","content"));

    }



    void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ValuationAdapter adapter = new ValuationAdapter(datas);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10,10,10,10));
    }

}
