package com.example.administrator.omg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserCommentsFragment extends Fragment {



    public UserCommentsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static UserCommentsFragment newInstance() {
        UserCommentsFragment fragment = new UserCommentsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
//        appCompatActivity.setSupportActionBar(null);
        return inflater.inflate(R.layout.fragment_user_comments, container, false);
    }

}
