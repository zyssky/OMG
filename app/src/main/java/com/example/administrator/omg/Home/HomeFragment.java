package com.example.administrator.omg.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.BottomNavigationViewHelper;
import com.example.administrator.omg.Charge.ChargeActivity;
import com.example.administrator.omg.IntroductionActivity;
import com.example.administrator.omg.SportActivity.SportAcActivity;
import com.example.administrator.omg.util.GlideImageLoader;
import com.example.administrator.omg.PlaceOrder.PlaceOrderActivity;
import com.example.administrator.omg.R;
import com.youth.banner.Banner;


public class HomeFragment extends Fragment implements HomeContract.View{
    public String TAG = Fragment.class.getSimpleName();

    private RecyclerView recyclerView;

    private BottomNavigationView navigationview;

    private HomePresenter presenter;

    private View header;

    private Context context;


    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter(this,HomeTestData.getInstance());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.home_recyclerview);

        header = LayoutInflater.from(context).inflate(R.layout.header_home,null);

        initBanner(header);
        initNavigationView(header);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        presenter.setHomeAdapter();

        return rootView;
    }


    private void initNavigationView(View rootView) {
        navigationview = (BottomNavigationView) rootView.findViewById(R.id.nav_others);
        BottomNavigationViewHelper.disableShiftMode(navigationview);
        navigationview.setSelectedItemId(R.id.nav_category);

        navigationview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()){
                    case R.id.nav_charge:
                        intent = new Intent(context, ChargeActivity.class);
                        break;
                    case R.id.nav_activity:
                        intent = new Intent(context, SportAcActivity.class);
                        break;
                    case R.id.nav_comment:
                        intent = new Intent(context, IntroductionActivity.class);
                        break;
                    default:
                        return true;
                }
                startActivity(intent);

                return true;
            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(navigationview!=null){
            navigationview.setSelectedItemId(R.id.nav_category);
        }
    }

    void initBanner(View rootView){
        Banner banner = (Banner) rootView.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(HomeTestData.getInstance().getBannerImages());
        banner.start();
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        if(adapter instanceof HomeAdapter){
            HomeAdapter homeAdapter = (HomeAdapter) adapter;
            homeAdapter.setHeaderView(header);
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void goToViewWith(long courtId) {
        Intent intent = new Intent(context,PlaceOrderActivity.class);
        intent.putExtra(AppContants.COURT_ID,courtId);
        startActivity(intent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}

