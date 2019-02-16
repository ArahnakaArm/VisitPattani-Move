package com.example.arahnaka.myapplication;

public class Upload {
    private String mName;
    private String mImageUrl;
    private String mDes;
    private String mLocal;
    private String mTel;
    private  static  String NameUpLoad;

    public String getTel() {
        return mTel;
    }

    public void setTel(String mTel) {
        this.mTel = mTel;
    }

    public String getDes() {
        return mDes;
    }

    public void setDes(String mDes) {
        this.mDes = mDes;
    }

    public String getLocal() {
        return mLocal;
    }

    public void setLocal(String mLocal) {
        this.mLocal = mLocal;
    }

    public String getTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    private  String mTime;


    public Upload() {
        //empty constructor needed
    }

    public Upload(String name, String imageUrl,String Des,String Local,String Time,String Tel) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
        mDes=Des;
        mLocal=Local;
        mTime=Time;
        mTel=Tel;
    }

    public String getName() {
        return mName;
    }

    public static void setNameUpload(String nameup){
        NameUpLoad = nameup;
    }

    public static  String getNameUpload(){
        return NameUpLoad;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}