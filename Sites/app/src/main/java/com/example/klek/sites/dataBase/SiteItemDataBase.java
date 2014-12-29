package com.example.klek.sites.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.klek.sites.data.ItemSite;

import java.util.ArrayList;

/**
 * Created by klek on 29.12.2014.
 */
public class SiteItemDataBase extends SQLiteOpenHelper {

    private static final String DB_NAME ="site_item.db";
    private static final int DB_VERSION = 1;
    //table
    private String table_name;
    public static final String BADTOP_TOP_TABLE_NAME = "badtop100";
    public static final String FREE_HOST_TABLE_NAME = "freehost";
    public static final String ZONE_TABLE_NAME = "zone";
    public static final String FOREIGN_TABLE_NAME = "foreign";
    public static final String UID = "_id";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String URL = "url";
    //public static final String COLUMS = " ("+ID+" ,"+NAME+" ,"+URL+" )";
    private SQLiteDatabase db;


    public SiteItemDataBase(Context context, String name) {
         super(context, name, null, DB_VERSION);
         table_name = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table "+ table_name+
                " (id PRIMARY KEY, name TEXT, url TEXT)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String del = "drop table if exists "+table_name;
        db.execSQL(del);
        onCreate(db);
    }

    public void insert(ItemSite site){
        Log.d("INPUT ", "start");
        db = this.getWritableDatabase();
        /*String sqlInput = "input into "+table_name+
                " (id,name,url) value " +
                site.getId()+" ,"+
                site.getName()+" ,"+
                site.getUrl()+" )";*/
        ContentValues values = new ContentValues();
        values.put("id", site.getId());
        values.put("name",site.getName());
        values.put("url",site.getUrl());

        db.insert(table_name, null, values);
        db.close();
        Log.w("DB","ok");
    }

    public ArrayList<ItemSite> readAll(){
        ArrayList<ItemSite> result = new ArrayList<ItemSite>();
        db = this.getWritableDatabase();
        Cursor cursor = db.query(table_name, new String[]{"id", "name", "url"}, null,null,null,null,null);
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            ItemSite site = new ItemSite(id,name,url);
            result.add(site);
        }
        db.close();
        return result;
    }
}
