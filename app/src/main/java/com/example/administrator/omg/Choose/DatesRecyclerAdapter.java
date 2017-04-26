package com.example.administrator.omg.Choose;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.omg.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */

public class DatesRecyclerAdapter extends RecyclerView.Adapter<DatesRecyclerAdapter.MyViewHolder> {

    private List<String> mdatas;

    public DatesRecyclerAdapter(List<String> mdatas){
        this.mdatas = mdatas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orderdetail_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.date.setText(mdatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView date;

        public MyViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.item_detail);
        }
    }
}
