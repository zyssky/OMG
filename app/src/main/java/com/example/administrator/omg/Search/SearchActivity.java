package com.example.administrator.omg.Search;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.Home.HomeTestData;
import com.example.administrator.omg.PlaceOrder.PlaceOrderActivity;
import com.example.administrator.omg.R;
import com.example.administrator.omg.util.BaseRecyclerAdapter;
import com.example.administrator.omg.util.SpaceItemDecoration;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ClassifyAdapter adapter;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        context = this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.searchtitle);


        recyclerView = (RecyclerView) findViewById(R.id.classify);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));

        adapter = new ClassifyAdapter(HomeTestData.getInstance().getAllCourts());
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                Intent intent = new Intent(context,PlaceOrderActivity.class);
                intent.putExtra(AppContants.COURT_ID,(long) data);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10,10,25,0));
    }

    public void goback(View view){
        this.finish();
    }
}
