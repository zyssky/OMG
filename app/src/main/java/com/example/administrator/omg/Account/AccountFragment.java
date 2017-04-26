package com.example.administrator.omg.Account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.Balance.BalanceActivity;
import com.example.administrator.omg.Coupon.CouponActivity;
import com.example.administrator.omg.Credit.CreditActivity;
import com.example.administrator.omg.Login.LoginActivity;
import com.example.administrator.omg.R;

public class AccountFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private CardView login;
    private CardView quan;
    private CardView jifen;
    private CardView logout;
    private CardView balance;

    private Context context;

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
        quan = (CardView) rootView.findViewById(R.id.quan);
        jifen = (CardView) rootView.findViewById(R.id.jifen);
        logout = (CardView) rootView.findViewById(R.id.logout);
        balance = (CardView) rootView.findViewById(R.id.balance);


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        setAccountName();
    }

    private void setAccountName() {
        SharedPreferences share = context.getSharedPreferences(AppContants.USER_PREFER,Context.MODE_PRIVATE);
        String account = share.getString(AppContants.ACCOUNT,null);
        if(account!=null){
            TextView tv = (TextView) login.findViewById(R.id.login_text);
            tv.setText(account);
        }
    }

    private void clearAccount(){
        SharedPreferences share = context.getSharedPreferences(AppContants.USER_PREFER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString(AppContants.ACCOUNT,null);
        editor.putString(AppContants.TOKEN,null);
        editor.commit();
        TextView tv = (TextView) login.findViewById(R.id.login_text);
        tv.setText(R.string.login);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCardViewsListeners(getActivity());
    }

    private void initCardViewsListeners(Context context) {
        CardViewsListeners listener = new CardViewsListeners(context);
        logout.setOnClickListener(this);
        login.setOnClickListener(listener);
        quan.setOnClickListener(listener);
        jifen.setOnClickListener(listener);
        balance.setOnClickListener(listener);
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
}

class CardViewsListeners implements View.OnClickListener{

    private Context context;

    public CardViewsListeners(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.login:
                intent = new Intent(context,LoginActivity.class);
                break;
            case R.id.quan:
                intent = new Intent(context,CouponActivity.class);
                break;
            case R.id.jifen:
                intent = new Intent(context,CreditActivity.class);
                break;
            case R.id.balance:
                intent = new Intent(context,BalanceActivity.class);
                break;
        }
        context.startActivity(intent);
    }
}
