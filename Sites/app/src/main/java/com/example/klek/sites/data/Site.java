package com.example.klek.sites.data;


public class Site {
    private String id;
    private String name;
    private String url;
    private String uniq_url;
    private String domain;
    private String govbody_name;
    //private String technology;

    public Site(String id, String name, String url, String uniq_url, String domain, String govbody_name/*, String technology*/){
        this.id = id;
        this.name = name;
        this.url =url;
        this.uniq_url = uniq_url;
        this.domain = domain;
        this.govbody_name = govbody_name;
        //this.technology = technology;
    }

    public String getId(){return id;}
    public String getName(){return name;}
    public String getUrl(){return url;}
    public String getUniq_url(){return uniq_url;}
    public String getDomain(){return domain;}
    public String getGovbody_name(){return govbody_name;}
   // public String getTechnology(){return technology;}
}
