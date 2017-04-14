package com.example.administrator.omg.PlaceOrder;

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
import com.example.administrator.omg.R;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity {

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
                Snackbar.make(view, R.string.warnning, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        switchType(getIntent().getExtras());

    }

    void setBarInform(int titleId,int imageId){
        toolbar.setTitle(getResources().getString(titleId));
//        toolbar.setTitle();
        Glide.with(this).load(imageId).into(barImageView);
    }

    private void switchType(Bundle extras) {
        int resId = extras.getInt(AppContants.SPORT_NAME);
        Bundle bundle = new Bundle();

        switch (resId){
            case R.id.badminton:
                setBarInform(R.string.badminton,R.drawable.badminton);
                bundle.putCharSequence(AppContants.SERVICE,getResources().getString(R.string.badminton_service));
                bundle.putCharSequence(AppContants.INTRODUCTION,getResources().getString(R.string.badminton_introduction));
                break;
            case R.id.tabletennis:
                setBarInform(R.string.tabletennis,R.drawable.tabletennis);
                bundle.putCharSequence(AppContants.SERVICE,getResources().getString(R.string.tabletennis_service));
                bundle.putCharSequence(AppContants.INTRODUCTION,getResources().getString(R.string.tabletennis_introduction));
                break;
            case R.id.tennis:
                setBarInform(R.string.tennis,R.drawable.tennis);
                bundle.putCharSequence(AppContants.SERVICE,getResources().getString(R.string.tennis_service));
                bundle.putCharSequence(AppContants.INTRODUCTION,getResources().getString(R.string.tennis_introduction));
                break;
            case R.id.basketball:
                setBarInform(R.string.basketball,R.drawable.basketball);
                bundle.putCharSequence(AppContants.SERVICE,getResources().getString(R.string.basketball_service));
                bundle.putCharSequence(AppContants.INTRODUCTION,getResources().getString(R.string.basketball_introduction));
                break;
            case R.id.football:
                setBarInform(R.string.football,R.drawable.football);
                bundle.putCharSequence(AppContants.SERVICE,getResources().getString(R.string.football_service));
                bundle.putCharSequence(AppContants.INTRODUCTION,getResources().getString(R.string.football_introduction));
                break;
            case R.id.swimming:
                setBarInform(R.string.swimming,R.drawable.swimming);
                bundle.putCharSequence(AppContants.SERVICE,getResources().getString(R.string.swimming_service));
                bundle.putCharSequence(AppContants.INTRODUCTION,getResources().getString(R.string.swimming_introduction));
                break;
        }
        ((PlaceOrderFragment)list_fragment.get(0)).setContent(bundle);
    }


    void initData(){
        list_fragment = new ArrayList<>();
        list_title = new ArrayList<>();
        list_title.add(getResources().getString(R.string.tab1));
        list_title.add(getResources().getString(R.string.tab2));

        list_fragment.add(PlaceOrderFragment.newInstance());
        list_fragment.add(UserValuationsFragment.newInstance());

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
