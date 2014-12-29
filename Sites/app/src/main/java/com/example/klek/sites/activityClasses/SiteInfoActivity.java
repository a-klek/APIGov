package com.example.klek.sites.activityClasses;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.klek.sites.APIConnection.APIConnector;
import com.example.klek.sites.R;
import com.example.klek.sites.adapter.SiteAdapter;
import com.example.klek.sites.data.ItemSite;
import com.example.klek.sites.data.Site;

import java.util.ArrayList;


public class SiteInfoActivity extends ActionBarActivity {
    ListView listView;
    ArrayList<ItemSite> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        //ProgressBar progressBar = (ProgressBar) findViewById(R.id.list_progress_bar);
        String id = getIntent().getStringExtra("id");
        setContentView(R.layout.list_activity);
        new AsyncConnection().execute(id);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);

    }

    private class AsyncConnection extends AsyncTask<String, Void, Site> {


        @Override
        protected Site doInBackground(String... id) {
            try {
                return APIConnector.siteFromJson(APIConnector.getSiteInfo(id[0]));
            }catch (Exception e){
                Log.d("err: ", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPreExecute(){super.onPreExecute();}

        @Override
        protected void onPostExecute(Site result){
            TextView id = (TextView) findViewById(R.id.site_info_id);
            TextView name = (TextView) findViewById(R.id.site_info_name);
            TextView url = (TextView) findViewById(R.id.site_info_url);
            TextView uniq_url = (TextView) findViewById(R.id.site_info_uniq_url);
            TextView govbody_name = (TextView) findViewById(R.id.site_info_govbady_name);
            TextView domain = (TextView) findViewById(R.id.site_info_domain);

            id.setText(result.getId());
            name.setText(result.getName());
            url.setText(result.getUrl());
            uniq_url.setText(result.getUniq_url());
            govbody_name.setText(result.getGovbody_name());
            domain.setText(result.getDomain());
        }
    }



}
