package com.example.androidstore.model;

public class DetailCategory {

    private String id;
    private String id_category;
    private String name;
    private String link_img;
    private String detail_category_id;

    public String getDetail_category_id() {
        return detail_category_id;
    }

    public void setDetail_category_id(String detail_category_id) {
        this.detail_category_id = detail_category_id;
    }

    public DetailCategory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }
}
