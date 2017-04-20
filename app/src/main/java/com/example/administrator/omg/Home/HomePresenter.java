package com.example.administrator.omg.Home;

import com.example.administrator.omg.MetaData.Court;
import com.example.administrator.omg.util.BaseRecyclerAdapter;

/**
 * Created by Administrator on 2017/4/19.
 */

public class HomePresenter {

    private HomeContract.View view;
    private HomeContract.Model model;

//    private ClassifyAdapter adapter;
    private HomeAdapter homeAdapter;

    public HomePresenter(HomeContract.View view,HomeContract.Model model){
        this.model = model;
        this.view  = view;
    }

    void setHomeAdapter(){
        homeAdapter = new HomeAdapter(model.getAllCourts());
        homeAdapter.setOnItemClickListener(new ItemClickListener());
        view.setAdapter(homeAdapter);
    }

    class ItemClickListener implements BaseRecyclerAdapter.OnItemClickListener{

        @Override
        public void onItemClick(int position, Object data) {
            if(data instanceof Court){
                view.goToViewWith(((Court) data).getId());
            }
        }
    }
}
