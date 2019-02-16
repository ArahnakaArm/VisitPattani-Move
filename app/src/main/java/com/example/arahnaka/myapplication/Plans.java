package com.example.arahnaka.myapplication;

public class Plans {
    String title,url,des,tel,type,location,cost,time;

    public Plans(String title, String url, String des, String tel, String type, String location, String cost, String time) {
        this.title = title;
        this.url = url;
        this.des = des;
        this.tel = tel;
        this.type = type;
        this.location = location;
        this.cost = cost;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { this.title = title; }

    public String getImage() {
        return url;
    }

    public void setImage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getDes() {
        return des;
    }

    public String getDescription() {
        return des;
    }

    public void setDescription(String description) {
        this.des = description;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public Plans(){



    }
}
