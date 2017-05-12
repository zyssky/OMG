package com.example.administrator.omg.History;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.MetaData.Valuation;
import com.example.administrator.omg.NetworkUtil;
import com.example.administrator.omg.PropUtil;
import com.example.administrator.omg.R;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GiveCommentActivity extends AppCompatActivity {
    private Button send;
    private Button cancle;
    private EditText thought;

    private Context context;

    private NetworkUtil.RxNetworkOperationListener listener;

    private Valuation valuation;

    private long orderId;

    private String courtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_comment);
        context = this;
        orderId = getIntent().getLongExtra(AppContants.ORDER_ID,-1);
        courtTitle = getIntent().getStringExtra("courtTitle");

        listener = new NetworkUtil.RxNetworkOperationListener() {
            @Override
            public String onCreateTask() {
                try {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(AppContants.USER_PREFER, Context.MODE_PRIVATE);
                    String token = sharedPreferences.getString(AppContants.TOKEN,null);
                    if(token==null){
                        return "";
                    }
                    String url = PropUtil.getCommentPublishUrl(context);
                    OkHttpClient httpClient = new OkHttpClient.Builder().build();


                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    RequestBody formBody = new FormBody.Builder()
                            .add("token", token)
                            .add("content",thought.getText().toString())
                            .add("courtTitle",courtTitle)
                            .add("id",orderId+"")
                            .add("date",simpleDateFormat.format(new Date()))
                            .build();
                    Request request = new Request.Builder()
                            .url(url)
                            .post(formBody)
                            .build();

                    Response response = httpClient.newCall(request).execute();
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    String body = response.body().string();
                    Log.d("发布评论 ", "doInBackground: "+body);

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
                            Toast.makeText(context,"发布成功",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(context,"发布失败",Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        };

        valuation = new Valuation();

        valuation.setOrderId(orderId);

        send = (Button) findViewById(R.id.comment_public);
        cancle = (Button) findViewById(R.id.comment_cancle);
        thought = (EditText) findViewById(R.id.thought);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuation.setContent(thought.getText().toString());
//                HistoryPresenter.getInstance().addCommment(valuation);
                NetworkUtil.create(listener).start();
                finish();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
