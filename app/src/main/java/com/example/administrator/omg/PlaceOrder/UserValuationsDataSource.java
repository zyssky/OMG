package com.example.administrator.omg.PlaceOrder;

import com.example.administrator.omg.MetaData.Valuation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public class UserValuationsDataSource {
    private static UserValuationsDataSource mInstance;
    private List<Valuation> mdatas;

    public static UserValuationsDataSource getInstance(){
        if(null==mInstance)
            mInstance = new UserValuationsDataSource();
        return mInstance;
    }

    private UserValuationsDataSource(){
        initData();
    }

    private void initData(){
        mdatas = new ArrayList<>();
        mdatas.add(new Valuation("user","date","qqqcontent"));
        mdatas.add(new Valuation("user","date","content"));
        mdatas.add(new Valuation("user","date","content"));
        mdatas.add(new Valuation("user","date","content"));
        mdatas.add(new Valuation("user","date","content"));
        mdatas.add(new Valuation("user","date","content"));
        mdatas.add(new Valuation("user","date","content"));
        mdatas.add(new Valuation("user","date","content"));
        mdatas.add(new Valuation("user","date","content"));
        mdatas.add(new Valuation("user","date","content"));
        mdatas.add(new Valuation("user","date","content"));
    }

    public List<Valuation> getValuations(){
        return mdatas;
    }
}
