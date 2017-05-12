package com.example.administrator.omg.Account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.Balance.BalanceActivity;
import com.example.administrator.omg.Coupon.CouponActivity;
import com.example.administrator.omg.Credit.CreditActivity;
import com.example.administrator.omg.Login.LoginActivity;
import com.example.administrator.omg.NetworkUtil;
import com.example.administrator.omg.PropUtil;
import com.example.administrator.omg.R;

import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AccountFragment extends Fragment implements View.OnClickListener,NetworkUtil.RxNetworkOperationListener{

    private View rootView;
    private CardView login;
//    private CardView quan;
    private CardView jifen;
    private CardView logout;
    private CardView balance;

    private Context context;

    private boolean once = true;

    public final static String TAG  = AccountFragment.class.getSimpleName();

    public AccountFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
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
        rootView = inflater.inflate(R.layout.fragment_account, container, false);
        login = (CardView) rootView.findViewById(R.id.login);
//        quan = (CardView) rootView.findViewById(R.id.quan);
        jifen = (CardView) rootView.findViewById(R.id.jifen);
        logout = (CardView) rootView.findViewById(R.id.logout);
        balance = (CardView) rootView.findViewById(R.id.balance);


        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        NetworkUtil.create(this).start();
//        if(once) {
//            setValueFromRecord();
//            once = false;
//        }
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if(hidden==false){
//            NetworkUtil.create(this).start();
//        }
//    }

    private void setValueFromRecord() {
        SharedPreferences share = context.getSharedPreferences(AppContants.USER_PREFER,Context.MODE_PRIVATE);
        String account = share.getString(AppContants.ACCOUNT,null);
        String balance_value = share.getString(AppContants.BALANCE,null);
        String credit_value = share.getString(AppContants.CREDIT,null);
        updateValue(account,balance_value,credit_value);
    }

    private void updateValue(String username,String balance_value , String credit_value){
        if(username!=null){
            TextView tv = (TextView) login.findViewById(R.id.login_text);
            tv.setText(username);
        }
        if(balance_value!=null){
            TextView tv = (TextView) balance.findViewById(R.id.balance_value);
            tv.setText(balance_value);
        }
        if(credit_value!=null){
            TextView tv = (TextView) jifen.findViewById(R.id.jifen_value);
            tv.setText(credit_value);
        }
    }

    private void clearAccount(){
        SharedPreferences share = context.getSharedPreferences(AppContants.USER_PREFER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString(AppContants.ACCOUNT,null);
        editor.putString(AppContants.TOKEN,null);
        editor.putString(AppContants.CREDIT,null);
        editor.putString(AppContants.BALANCE,null);
        editor.commit();
        updateValue("登录","*","*");
    }

    private void saveAccount(String account,String balance_value,String credit_value){
        SharedPreferences share = context.getSharedPreferences(AppContants.USER_PREFER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString(AppContants.ACCOUNT,account);
        editor.putString(AppContants.CREDIT,credit_value);
        editor.putString(AppContants.BALANCE,balance_value);
        editor.commit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCardViewsListeners(getActivity());
    }

    private void initCardViewsListeners(Activity context) {
        CardViewsListeners listener = new CardViewsListeners(context);
        logout.setOnClickListener(this);
        login.setOnClickListener(listener);
//        quan.setOnClickListener(listener);
//        jifen.setOnClickListener(listener);
//        balance.setOnClickListener(listener);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout:
                clearAccount();
                break;
        }
    }

    @Override
    public String onCreateTask() {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(AppContants.USER_PREFER,Context.MODE_PRIVATE);
            String token = sharedPreferences.getString(AppContants.TOKEN,null);
            if(token==null){
                return "";
            }
            String url = PropUtil.getUserUrl(context);
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
            Log.d(TAG, "doInBackground: "+body);

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
                String username = jsonObject.getString("username");
                String credit = jsonObject.getInt("credit")+"";
                DecimalFormat decimalFormat = new DecimalFormat("00.00");
                String balance = decimalFormat.format(jsonObject.getDouble("balance"));

                updateValue(username,balance,credit);
                saveAccount(username,balance,credit);

            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}

class CardViewsListeners implements View.OnClickListener{

    private Activity context;

    public CardViewsListeners(Activity context){
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.login:
                intent = new Intent(context,LoginActivity.class);
                break;
//            case R.id.quan:
//                intent = new Intent(context,CouponActivity.class);
//                break;
//            case R.id.jifen:
//                intent = new Intent(context,CreditActivity.class);
//                break;
//            case R.id.balance:
//                intent = new Intent(context,BalanceActivity.class);
//                break;
        }

        context.startActivity(intent);
    }
}
