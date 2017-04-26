package com.example.administrator.omg.Choose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.PlaceOrder.PlaceOrderContract;
import com.example.administrator.omg.PlaceOrder.PlaceOrderDatas;
import com.example.administrator.omg.PlaceOrder.PlaceOrderPresenter;
import com.example.administrator.omg.R;
import com.example.administrator.omg.util.DividerItemDecoration;

import java.util.List;

public class PickActivity extends AppCompatActivity {

    RecyclerView datesRecyclerView ;

//    private RecyclerView.Adapter adapter;

    private RadioGroup radioGroup;

    public static final int PICK_DATE = 0;
    public static final int PICK_TIME = 1;
    public static final int PICK_COUNT = 2;
    public static final int PICK_DESC = 3;

    private EditText desc;


    private int chooseType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_date);
//        datesRecyclerView = (RecyclerView) findViewById(R.id.dates_recyclerview);
//        datesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        datesRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
//
//        adapter = new DatesRecyclerAdapter(PlaceOrderDatas.getInstance().getDates());
//        datesRecyclerView.setAdapter(adapter);


        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        desc = (EditText) findViewById(R.id.desc);

        Intent intent = getIntent();
        chooseType = intent.getIntExtra(AppContants.CHOOSE_TYPE,-1);
        List<String> list;
        switch (chooseType){
            case PICK_DATE:
                list = PlaceOrderDatas.getInstance().getDates();
                addRadioButtons(list);
                break;
            case PICK_TIME:
                list = PlaceOrderDatas.getInstance().getTimes();
                addRadioButtons(list);
                break;
            case PICK_COUNT:
                List<Integer> integers = PlaceOrderDatas.getInstance().getCounts();
                addRadioButtonsAtCount(integers);
                break;
            case PICK_DESC:
                desc.setVisibility(View.VISIBLE);
                break;
        }


    }

    private void addRadioButtonsAtCount(List<Integer> list){
        for(int i=0;i<list.size();i++){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            radioButton.setText(""+list.get(i));
            radioButton.setPadding(5,5,5,0);
            radioButton.setTextSize(18);
            radioGroup.addView(radioButton);
        }
    }

    private void addRadioButtons(List<String> list){
        for(int i=0;i<list.size();i++){
            String item = list.get(i);
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            radioButton.setText(item);
            radioButton.setPadding(5,5,5,0);
            radioButton.setTextSize(18);
            radioGroup.addView(radioButton);
        }
    }

    public void onClickOk(View view){
        switch (chooseType){
            case PICK_DATE:
                PlaceOrderPresenter.getInstance().updateDate(radioGroup.getCheckedRadioButtonId());
                break;
            case PICK_TIME:
                PlaceOrderPresenter.getInstance().updateTime(radioGroup.getCheckedRadioButtonId());
                break;
            case PICK_COUNT:
                PlaceOrderPresenter.getInstance().updateCount(radioGroup.getCheckedRadioButtonId());
                break;
            case PICK_DESC:
                PlaceOrderPresenter.getInstance().updateDesc(desc.getText().toString());
        }
        finish();
    }

    public void onClickCancle(View view){
        finish();
    }
}
