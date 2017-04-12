package com.example.administrator.omg;

import android.os.Bundle;
import android.util.Log;


/**
 * Created by Administrator on 2017/3/31.
 */

public class LoginHandler {
    public static String TAG = LoginHandler.class.getSimpleName();

    public static void onLogin(Bundle bundle){
        Log.d(TAG, "OnLogin: "+ AppContants.ACCOUNT+" : "+bundle.getString(AppContants.ACCOUNT));
    }

    public static void onRegister(Bundle bundle){
        Log.d(TAG, "onRegister: "+ AppContants.ACCOUNT+" : "+bundle.getString(AppContants.ACCOUNT));
    }
}
