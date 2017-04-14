package com.example.administrator.omg.Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.omg.R;
import com.example.administrator.omg.util.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<String> mdatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.searchtitle);

        mdatas = new ArrayList<>();
        mdatas.add("羽毛球");
        mdatas.add("乒乓球");
        mdatas.add("网球");
        mdatas.add("篮球");
        mdatas.add("足球");
        mdatas.add("游泳");


        recyclerView = (RecyclerView) findViewById(R.id.classify);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setAdapter(new ClassifyAdapter(mdatas));
        recyclerView.addItemDecoration(new SpaceItemDecoration(10,10,25,0));
    }

    public void goback(View view){
        this.finish();
    }
}
