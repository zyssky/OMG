package com.example.administrator.omg.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.JsonToken;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.Home.MainActivity;
import com.example.administrator.omg.PropUtil;
import com.example.administrator.omg.R;

import java.io.IOException;


import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;

import org.json.JSONException;
import org.json.JSONObject;

public class AsyncLogin extends AsyncTask<String,Integer,Integer> {

    public final static int SUCCESS = 1;
    public final static int FAIL = 0;

    public static int TYPE = AppContants.LOGIN;

    public static final String TAG = AsyncLogin.class.getSimpleName();

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
            String url = PropUtil.getLoginUrl(context);
            if(TYPE==AppContants.REGISTER)
                url = PropUtil.getRegisterUrl(context);
            OkHttpClient httpClient = new OkHttpClient.Builder().build();

            RequestBody formBody = new FormBody.Builder()
                    .add("username", account)
                    .add("password",password)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            Response response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String body = response.body().string();
            Log.d(TAG, "doInBackground: "+body);


            JSONObject jsonObject = new JSONObject(body);
            if(jsonObject.getInt("status")==SUCCESS) {
                token = jsonObject.getString("token");

                saveUserInfo(token);
                return SUCCESS;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return FAIL;
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
                intent.putExtra("status",FAIL);
                context.startActivity(intent);
                break;
            case SUCCESS:
                intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("status",SUCCESS);
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
