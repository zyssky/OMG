package com.example.administrator.omg.Home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.omg.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.MyViewHolder> {

    private List<String> mdatas;

    public ClassifyAdapter(List<String> mdatas){
        this.mdatas = mdatas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classify,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mood.setText(mdatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView mood;

        public MyViewHolder(View itemView) {
            super(itemView);
            mood = (TextView) itemView.findViewById(R.id.type);
        }
    }
}
