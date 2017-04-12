package com.example.administrator.omg;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PlaceOrderFragment extends Fragment {


    private View rootView;

    private Bundle initBundle;

    public PlaceOrderFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PlaceOrderFragment newInstance() {
        PlaceOrderFragment fragment = new PlaceOrderFragment();

        return fragment;
    }

    public void setContent(Bundle bundle){
        initBundle = bundle;
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
        String introduction = initBundle.getString(AppContants.INTRODUCTION);
        String service = initBundle.getString(AppContants.SERVICE);
        TextView tv_intro = (TextView) rootView.findViewById(R.id.introduction);
        TextView tv_service = (TextView) rootView.findViewById(R.id.service);
        tv_intro.setText(introduction);
        tv_service.setText(service);
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
