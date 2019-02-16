package com.example.arahnaka.myapplication;

public  class Helps {
    String title,uurl,url,des,number,type,location;

    public Helps(String title, String uurl, String url, String des, String number, String type, String location) {
        this.title = title;
        this.uurl = uurl;
        this.url = url;
        this.des = des;
        this.number = number;
        this.type = type;
        this.location = location;

    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getUurl() {
        return uurl;
    }
    public void setUurl(String uurl) {
        this.uurl = uurl;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getDes() {
        return des;
    }
    public void  setDescc(String des) {
        this.des = des ;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public Helps(){


    }
}
