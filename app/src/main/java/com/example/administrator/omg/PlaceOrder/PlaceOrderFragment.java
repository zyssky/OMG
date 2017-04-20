package com.example.administrator.omg.PlaceOrder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.omg.MetaData.Court;
import com.example.administrator.omg.R;


public class PlaceOrderFragment extends Fragment {


    private View rootView;

    private Court court;

    public PlaceOrderFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PlaceOrderFragment newInstance() {
        PlaceOrderFragment fragment = new PlaceOrderFragment();

        return fragment;
    }

    public void setContent(Court court){
        this.court = court;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_place_order, container, false);

        initDetail();

        return rootView;
    }

    void initDetail(){
        String introduction = court.getDesc();
        String service = court.getService();
        TextView tv_intro = (TextView) rootView.findViewById(R.id.introduction);
        TextView tv_service = (TextView) rootView.findViewById(R.id.service);
        TextView tv_price = (TextView) rootView.findViewById(R.id.price);
        tv_intro.setText(introduction);
        tv_service.setText(service);
        tv_price.setText(court.getPrice()+"");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
