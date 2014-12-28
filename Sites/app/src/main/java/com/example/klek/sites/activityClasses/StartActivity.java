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



    protected String getAPI(){
        String result = APIConnector.getString(APIConnector.BAD_TOP_100);
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        Intent intent = new Intent(StartActivity.this, ListActivity.class);
        startActivity(intent);
    }

    public void onClick2(View view){
        new Async2().execute();
    }

    private class AsyncConnection extends AsyncTask<Void, Void, ArrayList<ItemSite>>{

        //ArrayList<ItemSite> results;
        StartActivity activity;
        public AsyncConnection(StartActivity activity){
            super();
            this.activity = activity;
        }
        @Override
        protected ArrayList<ItemSite> doInBackground(Void... params) {
            try {
                return APIConnector.itemFromJson(APIConnector.getListFromAPI(APIConnector.BAD_TOP_100));
            }catch (Exception e){
                Log.d("err: ",e.getMessage(),e);
            }
            return null;
        }

        @Override
        protected void onPreExecute(){super.onPreExecute();}

        @Override
        protected void onPostExecute(ArrayList<ItemSite> list){
            listView = (ListView) findViewById(R.id.list_view2);
            listView.setAdapter(new SiteAdapter(activity, list));
        }
    }

    private class Async2 extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... params) {
            //return APIConnector.getString(APIConnector.BAD_TOP_100);
            return APIConnector.getString(APIConnector.BAD_TOP_100);

        }
        @Override
        protected void onPreExecute(){super.onPreExecute();}
        @Override
        protected void onPostExecute(String result){
            if(result != null){
                super.onPostExecute(result);
                //ArrayList<ItemSite> list = APIConnector.itemFromJson(result);

                TextView textView = (TextView) findViewById(R.id.text_view_start);
                textView.setText(result);
            }
        }
    }
}
