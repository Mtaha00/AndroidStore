package com.example.androidstore.model.popular;

public class PopularFirst {

    private String title;
    private String link_img;

    public PopularFirst(String title, String link_img) {
        this.title = title;
        this.link_img = link_img;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }
}
