package com.example.administrator.omg.Home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.administrator.omg.Account.AccountFragment;
import com.example.administrator.omg.History.OrderFragment;
import com.example.administrator.omg.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private Context context;

    private BottomNavigationView navigationItemView;

    private ArrayList<Fragment> fragments;

    private Fragment curFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        context = this;
        initFragments();
        initNavigationView();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                Intent intent = new Intent(this,SearchActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    void initFragments(){
        fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(OrderFragment.newInstance());
        fragments.add(AccountFragment.newInstance());

        curFragment = fragments.get(0);

        getSupportFragmentManager().beginTransaction().add(R.id.container,curFragment).commit();

    }

    void initNavigationView(){

        navigationItemView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        navigationItemView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        switchContent(curFragment,fragments.get(0));
                        break;
                    case R.id.nav_orders:
                        switchContent(curFragment,fragments.get(1));
                        break;
                    case R.id.nav_account:
                        switchContent(curFragment,fragments.get(2));
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

    }

    public void switchContent(Fragment from, Fragment to) {
        if (curFragment != to) {
            curFragment = to;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().setCustomAnimations(
                    android.R.anim.fade_in, android.R.anim.fade_out);
            if (!to.isAdded()) {	// 先判断是否被add过
                transaction.hide(from).add(R.id.container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}