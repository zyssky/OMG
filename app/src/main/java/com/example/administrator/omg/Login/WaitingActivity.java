package com.example.administrator.omg.Login;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.Home.MainActivity;
import com.example.administrator.omg.R;

public class WaitingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        AsyncLogin.TYPE = getIntent().getIntExtra(AppContants.TYPE,-1);
        final String account = getIntent().getStringExtra(AppContants.ACCOUNT);
        final String password = getIntent().getStringExtra(AppContants.PASSWORD);

        new AsyncLogin(this).execute(account,password);


    }

}

