package com.example.administrator.omg.History;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.omg.MetaData.Order;
import com.example.administrator.omg.R;
import com.example.administrator.omg.util.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private List<Order> mdatas;

    public OrderAdapter(List<Order> datas){
        mdatas = datas;
    }

    private BaseRecyclerAdapter.OnItemClickListener mDeleteListener;

    private BaseRecyclerAdapter.OnItemClickListener mCommentListener;

    public void setmDeleteListener(BaseRecyclerAdapter.OnItemClickListener mDeleteListener) {
        this.mDeleteListener = mDeleteListener;
    }

    public void setmCommentListener(BaseRecyclerAdapter.OnItemClickListener mCommentListener) {
        this.mCommentListener = mCommentListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Order order = mdatas.get(position);
        holder.sportType.setText(order.getSportType());
        holder.count.setText(""+order.getCount());
        holder.date.setText(order.getDate());
        holder.desc.setText(order.getDesc());
        holder.price.setText(""+order.getSinglePrice());
        holder.time.setText(order.getTime());

        if(null!=mCommentListener){
            holder.take_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCommentListener.onItemClick(position,order);
                }
            });
        }

        if(null!=mDeleteListener){
            holder.take_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDeleteListener.onItemClick(position,order);
                }
            });
        }
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
        public Button take_comment;
        public Button take_delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            sportType = (TextView) itemView.findViewById(R.id.order_title);
            date = (TextView) itemView.findViewById(R.id.order_date);
            time = (TextView) itemView.findViewById(R.id.order_time);
            price = (TextView) itemView.findViewById(R.id.order_price);
            desc = (TextView) itemView.findViewById(R.id.order_desc);
            count = (TextView) itemView.findViewById(R.id.order_count);

            take_comment = (Button) itemView.findViewById(R.id.take_comment);
            take_delete = (Button) itemView.findViewById(R.id.take_delete);
        }
    }
}
