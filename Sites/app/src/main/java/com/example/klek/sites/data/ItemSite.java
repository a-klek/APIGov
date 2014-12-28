package com.example.klek.sites.data;


public class ItemSite {
    private String id;
    private String name;
    private String url;


    public ItemSite(String id, String name, String url){
        this.id = id;
        this.name = name;
        this.url =url;
    }

    public String getId(){return id;}
    public String getName(){return name;}
    public String getUrl(){return url;}

}
