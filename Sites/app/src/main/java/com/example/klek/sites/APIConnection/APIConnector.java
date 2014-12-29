package com.example.klek.sites.APIConnection;

import android.nfc.Tag;
import android.util.JsonReader;
import android.util.Log;

import com.example.klek.sites.data.ItemSite;
import com.example.klek.sites.data.Site;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.*;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.jar.JarEntry;


public class APIConnector {
    private static final String URL = "http://www.govweb.ru/api/v1/";
    private static final String FORMAT = "?format=json";
    //connection
    private static final String CONNECTION_METHOD = "GET";
    private static final int CONNECTION_TIMEOUT = 15000;
    private static final int READ_TIMEOUT = 10000;
    //request types:
    public static final String BAD_TOP_100 = "ratings/w3c_badtop100/";
    public static final String FOREIGN = "ratings/foreign/";
    public static final String ZONE = "ratings/zone/";
    public static final String FREE_HOSTING = "ratings/freehosting/";

    public static final String SITE_INFO = "common/siteinfo/?id=";



    public static JSONArray getListFromAPI(String request_type) {
        String urlString = URL + request_type + FORMAT;

        HttpURLConnection connection;
        URL url;
       // HttpClient client;
        //HttpResponse response;
        //HttpEntity entity;
        InputStream inputStream = null;
        //JSONObject jsonObject = null;
        JSONArray jsonArray=null;
        String connectionResult = "";
        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(CONNECTION_METHOD);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setDoInput(true);
            connection.connect();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while((line=bufferedReader.readLine())!=null){
                connectionResult += line;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            jsonArray = new JSONArray(connectionResult);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static JSONObject getSiteInfo(String id){
        String urlString = URL + SITE_INFO + id;

        HttpURLConnection connection;
        URL url;
        // HttpClient client;
        //HttpResponse response;
        //HttpEntity entity;
        InputStream inputStream = null;
        //JSONObject jsonObject = null;
        JSONObject jsonObject=null;
        String connectionResult = "";
        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(CONNECTION_METHOD);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setDoInput(true);
            connection.connect();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while((line=bufferedReader.readLine())!=null){
                connectionResult += line;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            jsonObject = new JSONObject(connectionResult);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static ArrayList<ItemSite> itemFromJson(JSONArray jsonArray){
        ArrayList<ItemSite> result = new ArrayList<ItemSite>();
        JSONObject jsonObject;
        try {
            int l = jsonArray.length();
            for(int i=0;i<l;i++){
                jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("website_name");
                String url = jsonObject.optString("website_url");
                result.add(new ItemSite(id,name,url));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static Site siteFromJson(JSONObject jsonObject){
        Site result = null;
        try {
            String id = jsonObject.getString("id");
            String name = jsonObject.getString("website_name");
            String url = jsonObject.optString("website_url");
            String uniq_url = jsonObject.getString("uniq_url");
            String domain = jsonObject.getString("domain");
            String govbody_name = jsonObject.getString("govbody_name");
            //String technology = jsonObject.getString("technologies");
            result = new Site(id, name, url, uniq_url, domain, govbody_name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
