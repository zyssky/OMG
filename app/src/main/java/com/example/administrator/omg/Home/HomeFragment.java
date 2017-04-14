package com.example.administrator.omg.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.omg.AppContants;
import com.example.administrator.omg.util.GlideImageLoader;
import com.example.administrator.omg.PlaceOrder.PlaceOrderActivity;
import com.example.administrator.omg.R;
import com.example.administrator.omg.TestData;
import com.youth.banner.Banner;

import java.lang.reflect.Field;


public class HomeFragment extends Fragment {
    public String TAG = Fragment.class.getSimpleName();

    private View rootView;

    private CardView badminton;
    private CardView tabletennis;
    private CardView tennis;
    private CardView basketball;
    private CardView football;
    private CardView swimming;

    public HomeFragment() {
        // Required empty public constructor
        Log.d(TAG, "HomeFragment: constructor");
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");

        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        initBanner(rootView);

//        initTestData(rootView);

        initCardViews(rootView);


        initNavigationView(rootView);

        return rootView;
    }

    private void testLoadImages(Context context) {
        ImageView view1 = (ImageView) badminton.findViewById(R.id.badminton_image);
        ImageView view2 = (ImageView) tabletennis.findViewById(R.id.tabletennis_image);
        ImageView view3 = (ImageView) tennis.findViewById(R.id.tennis_image);
        ImageView view4 = (ImageView) basketball.findViewById(R.id.basketball_image);
        ImageView view5 = (ImageView) football.findViewById(R.id.football_image);
        ImageView view6 = (ImageView) swimming.findViewById(R.id.swimming_image);

        Glide.with(context).load(R.drawable.badminton).thumbnail( 0.1f ).into(view1);
        Glide.with(context).load(R.drawable.tabletennis).thumbnail( 0.1f ).into(view2);
        Glide.with(context).load(R.drawable.tennis).thumbnail( 0.1f ).into(view3);
        Glide.with(context).load(R.drawable.basketball).thumbnail( 0.1f ).into(view4);
        Glide.with(context).load(R.drawable.football).thumbnail( 0.1f ).into(view5);
        Glide.with(context).load(R.drawable.swimming).thumbnail( 0.1f ).into(view6);

    }

    private void initCardViews(View rootView) {
        badminton = (CardView) rootView.findViewById(R.id.badminton);
        tabletennis = (CardView) rootView.findViewById(R.id.tabletennis);
        tennis = (CardView) rootView.findViewById(R.id.tennis);
        basketball = (CardView) rootView.findViewById(R.id.basketball);
        football = (CardView) rootView.findViewById(R.id.football);
        swimming = (CardView) rootView.findViewById(R.id.swimming);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListenersOnCardViews(new CardViewListeners(getActivity()));
        testLoadImages(getContext());
    }

    private void initListenersOnCardViews(View.OnClickListener cardViewListeners) {
        badminton.setOnClickListener(cardViewListeners);
        tabletennis.setOnClickListener(cardViewListeners);
        tennis.setOnClickListener(cardViewListeners);
        basketball.setOnClickListener(cardViewListeners);
        football.setOnClickListener(cardViewListeners);
        swimming.setOnClickListener(cardViewListeners);
    }

    private void initNavigationView(View rootView) {
        BottomNavigationView navigationview = (BottomNavigationView) rootView.findViewById(R.id.nav_others);
        BottomNavigationViewHelper.disableShiftMode(navigationview);
        navigationview.setSelectedItemId(R.id.nav_category);

        navigationview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_charge:
                        break;
                    case R.id.nav_activity:
                        break;
                    case R.id.nav_comment:
                        break;
                }
                return true;
            }

        });
    }

    void initBanner(View rootView){
        Banner banner = (Banner) rootView.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(TestData.getImages());
        banner.start();
    }

}

class CardViewListeners implements View.OnClickListener{
    private Context context;

    public CardViewListeners(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context,PlaceOrderActivity.class);

        switch (v.getId()){
            case R.id.badminton:
                intent.putExtra(AppContants.SPORT_NAME,R.id.badminton);
                break;
            case R.id.tabletennis:
                intent.putExtra(AppContants.SPORT_NAME,R.id.tabletennis);
                break;
            case R.id.tennis:
                intent.putExtra(AppContants.SPORT_NAME,R.id.tennis);
                break;
            case R.id.basketball:
                intent.putExtra(AppContants.SPORT_NAME,R.id.basketball);
                break;
            case R.id.football:
                intent.putExtra(AppContants.SPORT_NAME,R.id.football);
                break;
            case R.id.swimming:
                intent.putExtra(AppContants.SPORT_NAME,R.id.swimming);
                break;
        }
        context.startActivity(intent);
    }
}

// 利用反射机制，改变 item 的 mShiftingMode 变量
class BottomNavigationViewHelper {
    public static void disableShiftMode(BottomNavigationView navigationView) {
        BottomNavigationMenuView menuView =
                (BottomNavigationMenuView) navigationView.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            // Log
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // Log
            e.printStackTrace();
        }
    }
}
