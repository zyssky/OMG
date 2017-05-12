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
        court.setDesc("羽毛球场使用橡胶地板，专业防滑，场地界线清晰，羽毛球网每一年更换一次。场地使用柔和灯光，确保您不会在打球途中被灯光影响，我们竭诚为您服务！");
        court.setId(new Random().nextLong());
        court.setService("羽毛球馆提供专业的比赛用球的贩卖，您可以自己携带球或者到达球场时再行购买。场馆可以租借羽毛球拍，馆内有专门的休息室，有WiFi提供。");
        court.setPrice(12.8);
        mdatas.add(court);
        Court court1 = new Court();
        court1.setTitle("篮球");
        court1.setImage(R.drawable.basketball);
        court1.setDesc("篮球场使用专业的木质地板，确保您在打球过程中不会打滑，灯光使用柔和灯光，不刺眼，篮板为新型材料，扣篮无风险。");
        court1.setId(new Random().nextLong());
        court1.setService("馆内有篮球租借，同时有篮球充气工具免费使用。馆内有休息室，有饮料贩卖，免费WiFi上网。");
        court1.setPrice(32.8);
        mdatas.add(court1);
        Court court2 = new Court();
        court2.setTitle("游泳");
        court2.setImage(R.drawable.swimming);
        court2.setDesc("泳池保证每天换水，使用新技术检测水中尿素含量，保证您的健康");
        court2.setId(new Random().nextLong());
        court2.setService("游泳馆有专门的救生人员，确保您的人身安全，同时游泳馆内有泳衣出售，您可以自行携带泳装或者在馆内购买。");
        court2.setPrice(72.8);
        mdatas.add(court2);
        Court court3 = new Court();
        court3.setTitle("乒乓球");
        court3.setImage(R.drawable.tabletennis);
        court3.setId(new Random().nextLong());
        court3.setDesc("乒乓球桌采用比赛用桌，球网保证符合国际比赛标准，地板为橡胶地板，确保您在杀球过程中的安全不打滑。");
        court3.setService("馆内有免费的乒乓球提供，您可以使用馆内提供的球也可自行携带，球拍也有租借服务，满足您的所有要求。");
        court3.setPrice(20);
        mdatas.add(court3);
        Court court4 = new Court();
        court4.setTitle("足球");
        court4.setImage(R.drawable.football);
        court4.setDesc("足球场采用真草，每天进行维护，确保场地平整，球门球网定期更换，场外有医疗人员提供紧急救治服务。");
        court4.setService("足球可以租借，还有足球训练道具也可以免费使用。");
        court4.setId(new Random().nextLong());
        court4.setPrice(120);
        mdatas.add(court4);
        Court court6 = new Court();
        court6.setTitle("网球");
        court6.setImage(R.drawable.tennis);
        court6.setDesc("网球场为室内网球场，球网确保不会下垂，场地为标准比赛用地。");
        court6.setId(new Random().nextLong());
        court6.setService("网球场有计分以前可以使用，网球免费提供，您可以自行携带或者使用提供的网球，球拍馆内也可以租借，满足您的任何需求。");
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
