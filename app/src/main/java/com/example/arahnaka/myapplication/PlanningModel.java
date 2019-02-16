package com.example.arahnaka.myapplication;

public  class PlanningModel {
    String name;
    String duration;
    String image;
    String cost;
    String number;
    String level;
    String des;
    String des2;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes2() {
        return des2;
    }

    public void setDes2(String des2) {
        this.des2 = des2;
    }




    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getVechicle() {
        return vechicle;
    }

    public void setVechicle(String vechicle) {
        this.vechicle = vechicle;
    }

    String vechicle;

    public PlanningModel(String name, String duration, String image, String cost, String number, String vechicle, String faceline, String time) {
        this.name  = name;
        this.duration = duration;
        this.image = image;
        this.cost  = cost;
        this.number = number;
        this.vechicle = vechicle;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getImage() {
        return image;
    }

    public void setImage(String image) { this.image = image; }

    public String getDuration() { return duration; }

    public void setDuration(String duration){this.duration=duration;}





    public PlanningModel(){



    }
}
