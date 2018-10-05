package com.mrproducts.www.trending_repo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by webs on 12/20/2017.
 */

public class MyBaseAdapter extends BaseAdapter {
    ArrayList<ListData> myList = new ArrayList<ListData>();
    LayoutInflater inflater;
    Context context;
    private String s = "";
    int position;

    public MyBaseAdapter(Context context, ArrayList<ListData> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);

    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public ListData getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_repository, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }
        this.position = position;
        ListData currentListData = getItem(position);
        mViewHolder.name.setText(currentListData.getName());
        mViewHolder.link=currentListData.getLink();
        mViewHolder.desc.setText(currentListData.getLink());
        return convertView;
    }

    private class MyViewHolder {
        TextView name,desc;
        String link;
        Button  go;

        public MyViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.name);
            desc=(TextView) view.findViewById(R.id.desc);
            go = (Button) view.findViewById(R.id.go);


        }
    }
}


///