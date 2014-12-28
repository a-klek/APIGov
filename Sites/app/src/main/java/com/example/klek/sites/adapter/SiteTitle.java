package com.example.klek.sites.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.klek.sites.R;
import com.example.klek.sites.data.ItemSite;


public class SiteTitle extends RelativeLayout{
    private TextView id;
    private TextView name;
    private TextView url;

    public SiteTitle(Context context) {
        super(context);
        init(null, context);
    }

    public SiteTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs,context);
    }

    public SiteTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs,context);
    }

    protected void init(AttributeSet attributes, Context context){
        inflate(getContext(), R.layout.item_view,this);

        TypedArray arr = context.getTheme().obtainStyledAttributes(attributes, R.styleable.SiteTitle,0,0);

        try{
            String id = arr.getString(R.styleable.SiteTitle_id);
            String name = arr.getString(R.styleable.SiteTitle_name);
            String url = arr.getString(R.styleable.SiteTitle_url);

            this.id = (TextView) findViewById(R.id.website_id);
            this.name = (TextView) findViewById(R.id.website_name);
            this.url = (TextView) findViewById(R.id.website_url);

            this.id.setText(id);
            this.name.setText(name);
            this.url.setText(url);
            invalidate();
            requestLayout();
        }
        finally {
            arr.recycle();
        }
    }

    public void pop(ItemSite itemSite){
        id.setText(itemSite.getId());
        name.setText(itemSite.getName());
        url.setText(itemSite.getUrl());
    }
}
