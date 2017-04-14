package com.example.administrator.omg.History;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.omg.MetaData.Order;
import com.example.administrator.omg.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private List<Order> mdatas;

    public OrderAdapter(List<Order> datas){
        mdatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.sportType.setText(mdatas.get(position).getSportType());
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView sportType;
        public TextView date;
        public TextView time;
        public TextView price;
        public TextView desc;
        public TextView count;

        public MyViewHolder(View itemView) {
            super(itemView);
            sportType = (TextView) itemView.findViewById(R.id.order_title);
        }
    }
}
