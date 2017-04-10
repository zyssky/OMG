package com.example.administrator.omg;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private TabLayout mTabLayout;

    private List<String> list_title;

    private List<Fragment> list_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        initData();



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

    }

    void initData(){
        list_fragment = new ArrayList<>();
        list_title = new ArrayList<>();
        list_title.add(getResources().getString(R.string.tab1));
        list_title.add(getResources().getString(R.string.tab2));
        list_fragment.add(PlaceOrderFragment.newInstance());
        list_fragment.add(UserCommentsFragment.newInstance());
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
