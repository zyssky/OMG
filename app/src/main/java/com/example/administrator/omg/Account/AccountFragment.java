package com.example.administrator.omg.Account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.omg.Coupon.CouponActivity;
import com.example.administrator.omg.Credit.CreditActivity;
import com.example.administrator.omg.Login.LoginActivity;
import com.example.administrator.omg.R;

public class AccountFragment extends Fragment {

    private View rootView;
    private CardView login;
    private CardView quan;
    private CardView jifen;
    private CardView logout;

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

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCardViewsListeners(getActivity());
    }

    private void initCardViewsListeners(Context context) {
        CardViewsListeners listener = new CardViewsListeners(context);
        logout.setOnClickListener(listener);
        login.setOnClickListener(listener);
        quan.setOnClickListener(listener);
        jifen.setOnClickListener(listener);
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
            case R.id.logout:
                return;
        }
        context.startActivity(intent);
    }
}
