package com.example.klek.sites.activityClasses;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.klek.sites.R;
import com.example.klek.sites.adapter.SiteAdapter;
import com.example.klek.sites.data.ItemSite;
import com.example.klek.sites.APIConnection.APIConnector;

import java.util.ArrayList;


public class ListActivity extends ActionBarActivity {

    ListView listView;
    ArrayList<ItemSite> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        //ProgressBar progressBar = (ProgressBar) findViewById(R.id.list_progress_bar);
        list = APIConnector.itemFromJson(APIConnector.getListFromAPI(APIConnector.BAD_TOP_100));
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new SiteAdapter(this, list));

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        list = APIConnector.itemFromJson(APIConnector.getListFromAPI(APIConnector.BAD_TOP_100));
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new SiteAdapter(this, list));
    }

}
