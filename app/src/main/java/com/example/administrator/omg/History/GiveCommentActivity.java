package com.example.administrator.omg.History;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.MetaData.Valuation;
import com.example.administrator.omg.R;

public class GiveCommentActivity extends AppCompatActivity {
    private Button send;
    private Button cancle;
    private EditText thought;

    private Valuation valuation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_comment);

        valuation = new Valuation();
        long orderId = getIntent().getLongExtra(AppContants.ORDER_ID,-1);
        valuation.setOrderId(orderId);

        send = (Button) findViewById(R.id.comment_public);
        cancle = (Button) findViewById(R.id.comment_cancle);
        thought = (EditText) findViewById(R.id.thought);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuation.setContent(thought.getText().toString());
                HistoryPresenter.getInstance().addCommment(valuation);
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
