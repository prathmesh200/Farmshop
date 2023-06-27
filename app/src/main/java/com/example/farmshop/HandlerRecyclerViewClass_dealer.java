package com.example.farmshop;

import android.net.Uri;

public class HandlerRecyclerViewClass_dealer {

    String foodname,description,feedcount,phoneno;
    Uri image;

    public HandlerRecyclerViewClass_dealer(String description, String feedcount, String foodname, Uri image,String phoneno) {

        this.foodname = foodname;
        this.description = description;
        this.feedcount = feedcount;
        this.image = image;
        this.phoneno=phoneno;

    }

    public String getFoodname() {
        return foodname;
    }


    public String getPhoneno(){return phoneno;}

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeedcount() {
        return feedcount;
    }

    public void setFeedcount(String feedcount) {
        this.feedcount = feedcount;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

}
