package com.example.administrator.omg.Home;

import com.example.administrator.omg.MetaData.Court;
import com.example.administrator.omg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/4/19.
 */

public class HomeTestData implements HomeContract.Model {

    private static HomeContract.Model mInstance;
    private List<Court> mdatas;
//    private List<String> mTitles;
    private List<Object> mImages;

    protected HomeTestData(){
        mdatas = new ArrayList<>();
        Court court = new Court();
        court.setTitle("羽毛球");
        court.setImage(R.drawable.badminton);
        court.setDesc("这是羽毛球的描述");
        court.setId(new Random().nextLong());
        court.setService("这是羽毛球的服务");
        court.setPrice(12.8);
        mdatas.add(court);
        Court court1 = new Court();
        court1.setTitle("篮球");
        court1.setImage(R.drawable.basketball);
        court1.setDesc("这是篮球的描述");
        court1.setId(new Random().nextLong());
        court1.setService("这是篮球的服务");
        court1.setPrice(32.8);
        mdatas.add(court1);
        Court court2 = new Court();
        court2.setTitle("游泳");
        court2.setImage(R.drawable.swimming);
        court2.setDesc("这是游泳的描述");
        court2.setId(new Random().nextLong());
        court2.setService("这是游泳的服务");
        court2.setPrice(72.8);
        mdatas.add(court2);
        mdatas.add(court1);
        mdatas.add(court1);
        mdatas.add(court1);
        mdatas.add(court1);


        mImages = new ArrayList<>();
        mImages.add(R.drawable.badminton);
        mImages.add(R.drawable.basketball);
        mImages.add(R.drawable.swimming);
    }

    @Override
    public List<Court> getAllCourts() {
//        mdatas = new ArrayList<>();
        return mdatas;
    }


    @Override
    public Court getCourtById(long id) {
        for (Court court :
                mdatas) {
            if (id == court.getId())
                return court;
        }
        return null;
    }

    @Override
    public List<Object> getBannerImages() {

        return mImages;
    }

    @Override
    public void destroyInstance() {
        mInstance = null;
    }

    public static HomeContract.Model getInstance() {
        if(mInstance == null)
            mInstance = new HomeTestData();
        return mInstance;
    }
}
