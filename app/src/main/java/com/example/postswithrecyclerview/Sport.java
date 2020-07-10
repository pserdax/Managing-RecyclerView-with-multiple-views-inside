package com.example.postswithrecyclerview;

import com.google.gson.annotations.SerializedName;

public class Sport {

    @SerializedName("imageUrl")
    private String mImageUrl;
    @SerializedName("info")
    private String mInfo;
    @SerializedName("subtitle")
    private String mSubtitle;
    @SerializedName("title")
    private String mTitle;

    public Sport(String initialImageUrl, String initialInfo, String initialSubtitle, String initialTitle){

        this.mImageUrl = initialImageUrl;
        this.mInfo = initialInfo;
        this.mSubtitle = initialSubtitle;
        this.mTitle = initialTitle;
    }

    public String getImageUrl(){
        return this.mImageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.mImageUrl = imageUrl;
    }

    public String getInfo(){
        return this.mInfo;
    }

    public void setInfo(String info){
        this.mInfo = info;
    }

    public String getSubtitle(){
        return this.mSubtitle;
    }

    public void setSubtitle(String subtitle){
        this.mSubtitle = subtitle;
    }

    public String getTitle(){
        return this.mTitle;
    }

    public void setTitle(String title){
        this.mTitle = title;
    }
}
