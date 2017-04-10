package com.example.administrator.omg;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.youth.banner.Banner;

import java.lang.reflect.Field;


public class HomeFragment extends Fragment {
    public String TAG = Fragment.class.getSimpleName();

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

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        initBanner(rootView);

        initTestData(rootView);

        initNavigationView(rootView);

        return rootView;
    }

    private void initNavigationView(View rootView) {
        BottomNavigationView navigationview = (BottomNavigationView) rootView.findViewById(R.id.nav_others);
        BottomNavigationViewHelper.disableShiftMode(navigationview);

    }

    void initBanner(View rootView){
        Banner banner = (Banner) rootView.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(TestData.getImages());
        banner.start();
    }

    void initTestData(View rootView){
        Button testPlaceOrder = (Button) rootView.findViewById(R.id.testPlaceOrder);
        testPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PlaceOrderActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }
}


// 利用发射机制，改变 item 的 mShiftingMode 变量
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
