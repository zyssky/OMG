package com.example.administrator.omg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */

public class TestData {
    public static int[] ids = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
    };

    public static List<Integer> images;

    public static List<Integer> getImages(){
        if(images!=null)
            return images;
        images = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            images.add(ids[i]);
        }
        return images;
    }
}