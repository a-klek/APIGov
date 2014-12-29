package com.example.klek.sites.activityClasses;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.klek.sites.APIConnection.APIConnector;
import com.example.klek.sites.R;
import com.example.klek.sites.adapter.SiteAdapter;
import com.example.klek.sites.data.ItemSite;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class StartActivity extends ActionBarActivity {

    ListView listView;
    ArrayList<ItemSite> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }



    public void onClick(View view){
        int id = view.getId();
        String extra = "";
        switch (id){
            case R.id.badtop_button:
                extra=APIConnector.BAD_TOP_100;
                break;
            case R.id.foreign_button:
                extra=APIConnector.FOREIGN;
                break;
            case R.id.freehost_button:
                extra=APIConnector.FREE_HOSTING;
                break;
            case R.id.zone_button:
                extra=APIConnector.ZONE;
                break;
            default:
                TextView textView = (TextView) findViewById(R.id.text_view_start);
                textView.setText("ERROR");
                return;
        }
        Intent intent = new Intent(StartActivity.this, ListActivity.class);
        intent.putExtra("request", extra);
        startActivity(intent);
    }
}
