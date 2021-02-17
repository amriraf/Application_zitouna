package com.example.myapplication.models;

public class SliderItem {

    private String description;
    private String imageUrl;
    private int localImage;

    public int getLocalImage() {
        return localImage;
    }

    //public String getDescription() {
    //return description;
    //}

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setLocalImage(int localImage) {
        this.localImage = localImage;
    }
}