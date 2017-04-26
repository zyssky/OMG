package com.example.administrator.omg.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.Home.MainActivity;
import com.example.administrator.omg.R;

public class AsyncLogin extends AsyncTask<String,Integer,Integer> {

    public final static int SUCCESS = 1;
    public final static int FAIL = 0;

    public static int TYPE = AppContants.LOGIN;

    private Context context;
    private String account;
    private String password;

    public AsyncLogin(Context context){
        this.context = context;
    }


    @Override
    protected Integer doInBackground(String... params) {
        account = params[0];
        password = params[1];
        String token = "";
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        saveUserInfo(token);
        return 1;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        Intent intent;
        switch (integer){
            case FAIL:
                if(TYPE== AppContants.LOGIN)
                    Toast.makeText(context, R.string.login_fail,Toast.LENGTH_SHORT).show();
                if(TYPE==AppContants.REGISTER)
                    Toast.makeText(context,R.string.register_fail,Toast.LENGTH_SHORT).show();
                TYPE = AppContants.LOGIN;
                intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                break;
            case SUCCESS:
                intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Toast.makeText(context,R.string.login_success,Toast.LENGTH_SHORT).show();

                context.startActivity(intent);
        }
    }

    private void saveUserInfo(String token){
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppContants.USER_PREFER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppContants.ACCOUNT,account);
        editor.putString(AppContants.TOKEN,token);
        editor.commit();
    }
}
