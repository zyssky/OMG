package com.example.administrator.omg.PlaceOrder;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.MetaData.Court;
import com.example.administrator.omg.Home.HomeTestData;
import com.example.administrator.omg.MetaData.Order;
import com.example.administrator.omg.OrderConfirmActivity;
import com.example.administrator.omg.R;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity {

    private Context context;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private TabLayout mTabLayout;

    private List<String> list_title;

    private List<Fragment> list_fragment;

    private CollapsingToolbarLayout toolbar;

    private ImageView barImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        context = this;

        initData();


        toolbar =(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        barImageView = (ImageView) findViewById(R.id.barImage);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),list_fragment,list_title);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);

        for (int i = 0; i < list_title.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setTag(list_title.get(i)));
        }
        mTabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order order = PlaceOrderPresenter.getInstance().getCurOrder();
                if(!order.isOk()) {
                    Snackbar.make(view, R.string.warnning, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    Intent intent = new Intent(context, OrderConfirmActivity.class);
                    startActivity(intent);
                }

            }
        });

        Bundle bundle = getIntent().getExtras();
        long id = bundle.getLong(AppContants.COURT_ID);
        Court court = HomeTestData.getInstance().getCourtById(id);
        setBarInform(court.getTitle(),court.getImage());
        ((PlaceOrderFragment)list_fragment.get(0)).setContent(court);
        ((UserValuationsFragment)list_fragment.get(1)).setCourtTitle(court.getTitle());
    }

    void setBarInform(String title,Object imageId){
        toolbar.setTitle(title);

        Glide.with(this).load(imageId).into(barImageView);
    }

    void initData(){
        list_fragment = new ArrayList<>();
        list_title = new ArrayList<>();
        list_title.add(getResources().getString(R.string.tab1));
        list_title.add(getResources().getString(R.string.tab2));

        list_fragment.add(PlaceOrderFragment.newInstance());
        list_fragment.add(UserValuationsFragment.newInstance());

    }

    public void decCount(View view){
        PlaceOrderPresenter.getInstance().tryToDecCount();
    }

    public void addCount(View view){
        PlaceOrderPresenter.getInstance().tryToAddCount();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> listFragment;
        private List<String> listTitle;

        public SectionsPagerAdapter(FragmentManager fm,List<Fragment> listFragment,List<String> listTitle) {
            super(fm);
            this.listFragment = listFragment;
            this.listTitle = listTitle;
        }

        @Override
        public Fragment getItem(int position) {
            if(position<listFragment.size())
                return listFragment.get(position);
            return null;
        }

        @Override
        public int getCount() {
            return listTitle.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position<listTitle.size())
                return listTitle.get(position);
            return null;
        }
    }
}
