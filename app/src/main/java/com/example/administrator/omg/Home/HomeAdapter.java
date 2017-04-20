package com.example.administrator.omg.Home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.omg.MetaData.Court;
import com.example.administrator.omg.R;
import com.example.administrator.omg.util.BaseRecyclerAdapter;
import com.example.administrator.omg.util.MyImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */

public class HomeAdapter extends BaseRecyclerAdapter<Court> {
//    private List<Court> mdatas;

    public HomeAdapter(List<Court> mdatas){
        addDatas(mdatas);
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Court data) {
        MyViewHolder viewHolder1 = (MyViewHolder) viewHolder;
        MyImageLoader.getInstance().loadImage(viewHolder1.imageView,(Integer) data.getImage());
        viewHolder1.title.setText(data.getTitle());
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView title;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.card_image);
            title = (TextView) itemView.findViewById(R.id.card_text);
        }
    }
}
