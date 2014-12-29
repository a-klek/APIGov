package com.example.klek.sites.activityClasses;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.klek.sites.R;
import com.example.klek.sites.adapter.SiteAdapter;
import com.example.klek.sites.data.ItemSite;
import com.example.klek.sites.APIConnection.APIConnector;
import com.example.klek.sites.dataBase.SiteItemDataBase;

import java.util.ArrayList;


public class ListActivity extends ActionBarActivity {

    ListView listView;
    ArrayList<ItemSite> list;
    String request;
    SiteItemDataBase dataBase;
    String db_table_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        //ProgressBar progressBar = (ProgressBar) findViewById(R.id.list_progress_bar);
        request = getIntent().getStringExtra("request");
        setContentView(R.layout.list_activity);
        //new AsyncConnection().execute(request);
        //String db_table_name;
        switch(request){
            case APIConnector.BAD_TOP_100:
                db_table_name= SiteItemDataBase.BADTOP_TOP_TABLE_NAME;
                break;
            case APIConnector.FOREIGN:
                db_table_name=SiteItemDataBase.FOREIGN_TABLE_NAME;
                break;
            case APIConnector.FREE_HOSTING:
                db_table_name=SiteItemDataBase.FREE_HOST_TABLE_NAME;
                break;
            case APIConnector.ZONE:
                db_table_name=SiteItemDataBase.ZONE_TABLE_NAME;
                break;
            default:
                TextView textView = (TextView) findViewById(R.id.list_text_view);
                textView.setText("ERROR");
                return;
        }
        dataBase = new SiteItemDataBase(this, db_table_name);
        try {

            list=dataBase.readAll();

            //dataBase.close();
        }catch (Exception e){
            Log.d("ERROR", e.getMessage());
        }
        if(list != null && list.size()!=0) {
            listView = (ListView) findViewById(R.id.list_view);
            listView.setAdapter(new SiteAdapter(this, list));
        }
        else{Log.d("ERROR","list is empty");}
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        ListView listView1 = (ListView) findViewById(R.id.list_view);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TextView textView = (TextView) findViewById(R.id.list_text_view);
                //textView.setText(""+position);
                String extra = list.get(position).getId();
                TextView textView = (TextView) findViewById(R.id.list_text_view);
                textView.setText(extra);
                //Intent intent = new Intent(ListActivity.this, SiteInfoActivity.class);
                //intent.putExtra("id",extra);
                //startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update) {
            //TextView textView = (TextView) findViewById(R.id.list_text_view);
            //textView.setText("update");
            new AsyncConnection().execute(request);
            //dataBase = new SiteItemDataBase(this, db_table_name);
        }

        return super.onOptionsItemSelected(item);
    }

    private class AsyncConnection extends AsyncTask<String, Void, ArrayList<ItemSite>> {

        //ArrayList<ItemSite> results;

        @Override
        protected ArrayList<ItemSite> doInBackground(String... request) {
            try {
                return APIConnector.itemFromJson(APIConnector.getListFromAPI(request[0]));
            }catch (Exception e){
                Log.d("err: ", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPreExecute(){super.onPreExecute();}

        @Override
        protected void onPostExecute(ArrayList<ItemSite> list){
            getList(list);
        }
    }

    protected void getList(ArrayList<ItemSite> list){

        //this.list = list;
        Log.d("APIConn", "ok");
        dataBase = new SiteItemDataBase(this, db_table_name);
        Log.d("DB","start");
        for(int i=0;i<list.size();i++){
            Log.w("LIST "," site input");
            dataBase.insert(list.get(i));

        }
        this.list=dataBase.readAll();

        dataBase.close();

        if(this.list != null && this.list.size()!=0) {
            listView = (ListView) findViewById(R.id.list_view);
            listView.setAdapter(new SiteAdapter(this, this.list));
        }else{Log.d("ERROR","list is empty");}

        return;
    }


}
