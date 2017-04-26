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
        Court court3 = new Court();
        court3.setTitle("乒乓球");
        court3.setImage(R.drawable.tabletennis);
        court3.setId(new Random().nextLong());
        court3.setDesc("这是乒乓球的描述");
        court3.setService("这是乒乓球的服务");
        court3.setPrice(20);
        mdatas.add(court3);
        Court court4 = new Court();
        court4.setTitle("足球");
        court4.setImage(R.drawable.football);
        court4.setDesc("这是足球的描述");
        court4.setService("这是足球的服务");
        court4.setId(new Random().nextLong());
        court4.setPrice(120);
        mdatas.add(court4);
        Court court6 = new Court();
        court6.setTitle("网球");
        court6.setImage(R.drawable.tennis);
        court6.setDesc("这是网球的描述");
        court6.setId(new Random().nextLong());
        court6.setService("这是网球的服务");
        court6.setPrice(42);
        mdatas.add(court6);
//        mdatas.add(court1);
//        mdatas.add(court1);
//        mdatas.add(court1);
//        mdatas.add(court1);


        mImages = new ArrayList<>();
        mImages.add(R.drawable.badminton);
        mImages.add(R.drawable.basketball);
        mImages.add(R.drawable.swimming);
        mImages.add(R.drawable.tabletennis);
        mImages.add(R.drawable.tennis);
        mImages.add(R.drawable.football);
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
