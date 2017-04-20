package com.example.administrator.omg.Home;

import android.support.v7.widget.RecyclerView;

import com.example.administrator.omg.MetaData.Court;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */

public interface HomeContract {
    interface View{
        void setAdapter(RecyclerView.Adapter adapter);
        void goToViewWith(long courtId);
    }

    interface Model{
        List<Court> getAllCourts();

        Court getCourtById(long id);

        List<Object> getBannerImages();

        void destroyInstance();

    }
}
