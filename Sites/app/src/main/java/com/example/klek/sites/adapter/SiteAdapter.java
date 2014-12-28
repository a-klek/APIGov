package com.example.klek.sites.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.klek.sites.R;
import com.example.klek.sites.data.ItemSite;

import java.util.ArrayList;

public class SiteAdapter extends ArrayAdapter<ItemSite>{
    private ArrayList<ItemSite> list;

    public SiteAdapter(Context context, ArrayList<ItemSite> list) {
        super(context, R.layout.item_view);
        this.list = list;
    }
    @Override
    public int getCount(){return list.size();}

    @Override
    public View getView(int pos, View convert, ViewGroup parent){
        SiteTitle title = (SiteTitle) convert;

        if(title == null){
            title = new SiteTitle(getContext());
        }
        title.pop(list.get(pos));
        return title;
    }
}
