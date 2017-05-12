package com.example.administrator.omg.PlaceOrder;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.History.HistoryPresenter;
import com.example.administrator.omg.MetaData.Order;
import com.example.administrator.omg.NetworkUtil;
import com.example.administrator.omg.PropUtil;
import com.example.administrator.omg.R;
import com.example.administrator.omg.util.SpaceItemDecoration;
import com.example.administrator.omg.MetaData.Valuation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.administrator.omg.History.OrderFragment.getDay;

public class UserValuationsFragment extends Fragment {


    private RecyclerView recyclerView;

    private View rootView;

    private Context context;

    private String courtTitle;

    private ValuationAdapter adapter;

    private NetworkUtil.RxNetworkOperationListener listener;

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
    public void onResume() {
        super.onResume();
        NetworkUtil.create(listener).start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user_comments, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.valuations_recyView);

        return rootView;
    }

    public void setCourtTitle(String title){
        this.courtTitle = title;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRecyclerView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ValuationAdapter(new ArrayList<Valuation>());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10, 10, 10, 10));

        listener = new NetworkUtil.RxNetworkOperationListener() {
            @Override
            public String onCreateTask() {
                try {
//                    SharedPreferences sharedPreferences = context.getSharedPreferences(AppContants.USER_PREFER, Context.MODE_PRIVATE);
//                    String token = sharedPreferences.getString(AppContants.TOKEN,null);
//                    if(token==null){
//                        return "";
//                    }
                    String url = PropUtil.getCommentUrl(context);
                    url += "/" + courtTitle;
                    OkHttpClient httpClient = new OkHttpClient.Builder().build();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    Response response = httpClient.newCall(request).execute();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    String body = response.body().string();
                    Log.d("OrderFragment", "doInBackground: " + body);

                    return body;

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "";
            }

            @Override
            public void onCompleteTask(String content) {
                if (null!=content && !content.isEmpty() && !content.equals("null")) {
                    try {
                        JSONObject jsonObject = new JSONObject(content);
                        JSONArray jsonArray = jsonObject.getJSONArray("comment");
                        adapter.clearDatas();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            String username = jsonObject.getString("username");
                            String date = jsonObject.getString("date");
                            String content_value = jsonObject.getString("content");
                            date = getDay(date);

                            Valuation valuation = new Valuation(username, date, content_value);
                            adapter.addComment(valuation);
                        }
                        adapter.notifyDataChange();
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            JSONObject jsonObject = new JSONObject(content);
                            jsonObject = jsonObject.getJSONObject("comment");
                            adapter.clearDatas();
                            String username = jsonObject.getString("username");
                            String date = jsonObject.getString("date");
                            String content_value = jsonObject.getString("content");
                            date = getDay(date);

                            Valuation valuation = new Valuation(username, date, content_value);
                            adapter.addComment(valuation);
                            adapter.notifyDataChange();

                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                    }
                }

            }

        };
    }

}
