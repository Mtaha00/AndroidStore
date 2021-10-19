package com.example.androidstore.model;

import com.google.gson.annotations.Expose;

public class Banner {


    private int id;
    private String link_img;

    public Banner() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }
}
