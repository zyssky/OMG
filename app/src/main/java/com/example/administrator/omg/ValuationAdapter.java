package com.example.administrator.omg;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */

public class ValuationAdapter extends RecyclerView.Adapter<ValuationAdapter.MyViewHolder> {
    private List<Valuation> mdatas;

    public ValuationAdapter(List<Valuation> mdatas){
        this.mdatas = mdatas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.valution,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.user.setText(mdatas.get(position).getUser());
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView user;
        public TextView date;
        public TextView content;

        public MyViewHolder(View itemView) {
            super(itemView);
            user = (TextView) itemView.findViewById(R.id.valuation_user);
            date = (TextView) itemView.findViewById(R.id.valuation_date);
            content = (TextView) itemView.findViewById(R.id.valuation_content);
        }
    }
}
