package com.example.administrator.omg.PlaceOrder;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.omg.R;
import com.example.administrator.omg.util.GlideImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */

public class SimpleExpanListAdapter extends BaseExpandableListAdapter{

    private List<List<String>> itemList;
    private List<String> groupList;
    private List<Integer> imageResIds;

    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;

    public SimpleExpanListAdapter(List<List<String>> itemlist,List<String> grouplist,List<Integer> imageResIds){
        this.itemList = itemlist;
        this.groupList = grouplist;
        this.imageResIds = imageResIds;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return itemList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition*childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orderdetail_group,parent,false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.item_group_title);
        ImageView iv = (ImageView) convertView.findViewById(R.id.item_group_image);
        new GlideImageLoader().displayImage(context,imageResIds.get(groupPosition),iv);
        tv.setText(groupList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orderdetail_item,parent,false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.item_detail);
        tv.setText(itemList.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
