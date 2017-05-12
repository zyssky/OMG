package com.example.administrator.omg.History;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.MetaData.Order;
import com.example.administrator.omg.NetworkUtil;
import com.example.administrator.omg.PropUtil;
import com.example.administrator.omg.R;
import com.example.administrator.omg.util.BaseRecyclerAdapter;
import com.example.administrator.omg.util.SpaceItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment implements HistoryContract.View {

    private View rootView;
    private RecyclerView recyclerView;
    private Context context;

    private long curOrderId;

    private SwipeRefreshLayout refreshLayout;

    private HistoryContract.Presenter presenter;

    private BaseRecyclerAdapter.OnItemClickListener commentListener;
    private BaseRecyclerAdapter.OnItemClickListener deleteListener;

    private NetworkUtil.RxNetworkOperationListener listener;
    private NetworkUtil.RxNetworkOperationListener deletel;

    private OrderAdapter adapter;


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

        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh);

        return rootView;
    }

    public static String getDay(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
        Date temp = simpleDateFormat.parse(date);
        return simpleDateFormatDay.format(temp);
    }

    public static String getHour(String time) throws ParseException {
        SimpleDateFormat simpleDateFormatHour = new SimpleDateFormat("HH:mm");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date temp = simpleDateFormat.parse(time);
        return simpleDateFormatHour.format(temp);
    }

    @Override
    public void onResume() {
        super.onResume();
        NetworkUtil.create(listener).start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.setAdapterOnRecyclerView();
        recyclerView.addItemDecoration(new SpaceItemDecoration(10,10,10,10));

        deletel = new NetworkUtil.RxNetworkOperationListener() {
            @Override
            public String onCreateTask() {
                try {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(AppContants.USER_PREFER, Context.MODE_PRIVATE);
                    String token = sharedPreferences.getString(AppContants.TOKEN,null);
                    if(token==null){
                        return "";
                    }
                    String url = PropUtil.getOrderUrl(context);
                    url += "/delete";
                    OkHttpClient httpClient = new OkHttpClient.Builder().build();

                    FormBody formBody = new FormBody.Builder()
                            .add("token",token)
                            .add("id",curOrderId+"")
                            .build();

                    Request request = new Request.Builder()
                            .url(url)
                            .post(formBody)
                            .build();

                    Response response = httpClient.newCall(request).execute();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    String body = response.body().string();
                    Log.d("delete comment", "doInBackground: " + body);

                    return body;

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "";
            }

            @Override
            public void onCompleteTask(String content) {
                if(!content.isEmpty()){
                    try {
                        JSONObject jsonObject = new JSONObject(content);
//                        String username = jsonObject.getString("username");
//                        String credit = jsonObject.getInt("credit")+"";
//                        String balance = jsonObject.getDouble("balance")+"";
//
//                        updateValue(username,balance,credit);
//                        saveAccount(username,balance,credit);
                        int status = jsonObject.getInt("status");
                        if(1 == status){
                            ((OrderAdapter) adapter).deleteOrder(curOrderId);
                            Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(context,"删除失败",Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        };

        listener = new NetworkUtil.RxNetworkOperationListener() {
            @Override
            public String onCreateTask() {
                try {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(AppContants.USER_PREFER,Context.MODE_PRIVATE);
                    String token = sharedPreferences.getString(AppContants.TOKEN,null);
                    if(token==null){
                        return "";
                    }
                    String url = PropUtil.getOrderUrl(context);
                    OkHttpClient httpClient = new OkHttpClient.Builder().build();

                    RequestBody formBody = new FormBody.Builder()
                            .add("token", token)
                            .build();
                    Request request = new Request.Builder()
                            .url(url)
                            .post(formBody)
                            .build();

                    Response response = httpClient.newCall(request).execute();
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    String body = response.body().string();
                    Log.d("OrderFragment", "doInBackground: "+body);

                    return body;

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "";
            }

            @Override
            public void onCompleteTask(String content) {
                if(!content.isEmpty()){
                    try {
                        JSONObject jsonObject = new JSONObject(content);
                        JSONArray jsonArray = jsonObject.getJSONArray("order");
                        HistoryPresenter.getInstance().getAdapter().clearOrders();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            String countNumString = jsonObject.getString("countNum");
                            String courTitle = jsonObject.getString("courtTitle");
                            String date = jsonObject.getString("dateString");
                            String endTime = jsonObject.getString("endTime");
                            String startTime = jsonObject.getString("startTime");
                            String idString = jsonObject.getString("id");
                            String totalString = jsonObject.getString("total");
                            double total = Double.parseDouble(totalString);
                            int countNum = Integer.parseInt(countNumString);
                            long id = Long.parseLong(idString);
                            date = getDay(date);
                            endTime = getHour(endTime);
                            startTime = getHour(startTime);
                            String time = startTime+"-"+endTime;
                            Order order = new Order(id,courTitle,date,time,countNum,total/countNum,total);
                            HistoryPresenter.getInstance().getAdapter().addOrder(order);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NetworkUtil.create(listener).start();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void setAdapter(final RecyclerView.Adapter adapter) {
        if(adapter instanceof OrderAdapter){
            this.adapter = (OrderAdapter) adapter;
            if(commentListener==null){
                commentListener = new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object data) {
                        if(data instanceof Order) {
                            Order order = (Order) data;
                            Intent intent = new Intent(context, GiveCommentActivity.class);
                            intent.putExtra(AppContants.ORDER_ID, order.getId());
                            intent.putExtra("courtTitle",order.getSportType());
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
                            curOrderId = order.getId();
                            NetworkUtil.create(deletel).start();

                        }
                    }
                };
            }

            this.adapter.setmCommentListener(commentListener);
            this.adapter.setmDeleteListener(deleteListener);
        }
        recyclerView.setAdapter(adapter);
    }

}

