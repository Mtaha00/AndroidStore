package com.example.androidstore.model.popular;

public class PopularAmazing {

    private String id;
    private String category_id;
    private String detail_category_id;
    private String name;
    private String link_img;

    private String price;
    private String value_off;
    private String brand;
    private String offPrice;

    public PopularAmazing() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getValue_off() {
        return value_off;
    }

    public void setValue_off(String value_off) {
        this.value_off = value_off;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOffPrice() {
        return offPrice;
    }

    public void setOffPrice(String offPrice) {
        this.offPrice = offPrice;
    }


    public String getDetail_category_id() {
        return detail_category_id;
    }

    public void setDetail_category_id(String detail_category_id) {
        this.detail_category_id = detail_category_id;
    }

}
