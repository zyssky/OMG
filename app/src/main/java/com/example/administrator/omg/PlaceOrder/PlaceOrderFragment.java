package com.example.administrator.omg.PlaceOrder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.Choose.PickActivity;
import com.example.administrator.omg.Choose.PickTimeActivity;
import com.example.administrator.omg.MetaData.Court;
import com.example.administrator.omg.R;


public class PlaceOrderFragment extends Fragment implements View.OnClickListener , PlaceOrderContract.View {


    private View rootView;

    private Court court;

    private Context context;

    private CardView date;
    private CardView time;
    private CardView count;
//    private CardView desc;

    private PlaceOrderContract.Presenter presenter;

//    private ExpandableListView order_detail;

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
        PlaceOrderPresenter.initPresenter(this,new PlaceOrderDatas());
        presenter = PlaceOrderPresenter.getInstance();
        presenter.getCurOrder().setSinglePrice(court.getPrice());
        presenter.getCurOrder().setSportType(court.getTitle());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_place_order, container, false);

        date = (CardView) rootView.findViewById(R.id.choose_date);
        time = (CardView) rootView.findViewById(R.id.choose_time);
        count = (CardView) rootView.findViewById(R.id.choose_count);
//        desc = (CardView) rootView.findViewById(R.id.choose_desc);

//        order_detail = (ExpandableListView) rootView.findViewById(R.id.order_detail);
//        ExpandableListAdapter adapter = presenter.getExpandListAdapter();
//        if(adapter instanceof SimpleExpanListAdapter){
//            ((SimpleExpanListAdapter) adapter).setContext(context);
//        }
//        order_detail.setAdapter(adapter);
//        order_detail.setGroupIndicator(getResources().getDrawable(R.drawable.ic_account_circle_black_24px));
//
//        order_detail.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                return false;
//            }
//        });

        date.setOnClickListener(this);
        time.setOnClickListener(this);
        count.setOnClickListener(this);
//        desc.setOnClickListener(this);


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
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        Intent  intent = new Intent(context, PickActivity.class);

        switch (v.getId()){
            case R.id.choose_date:
                intent.putExtra(AppContants.CHOOSE_TYPE,PickActivity.PICK_DATE);
                break;
            case R.id.choose_time:
                intent.putExtra(AppContants.CHOOSE_TYPE,PickActivity.PICK_TIME);
                break;
//            case R.id.choose_count:
//                intent.putExtra(AppContants.CHOOSE_TYPE,PickActivity.PICK_COUNT);
//                break;
//            case R.id.choose_desc:
//                intent.putExtra(AppContants.CHOOSE_TYPE,PickActivity.PICK_DESC);
//                break;
            default:
                return;
        }
        context.startActivity(intent);
    }


    @Override
    public void setPickDate(String date) {
        TextView tv = (TextView) this.date.findViewById(R.id.tv_date);
        tv.setText(date);
    }

    @Override
    public void setPickTime(String time) {
        TextView tv = (TextView) this.time.findViewById(R.id.tv_time);
        tv.setText(time);
    }

    @Override
    public void addCount() {
        TextView textView = (TextView) this.count.findViewById(R.id.count_num);
        int c = Integer.parseInt(textView.getText().toString())+1;
        textView.setText(c+"");
    }

    @Override
    public void decCount() {
        TextView textView = (TextView) this.count.findViewById(R.id.count_num);
        int c = Integer.parseInt(textView.getText().toString())-1;
        textView.setText(c+"");
    }

    @Override
    public void setPickDesc(String desc) {
//        TextView tv = (TextView) this.desc.findViewById(R.id.tv_desc);
//        tv.setText(desc);
    }
}
