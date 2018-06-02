package com.example.pablo.laboratoriomenu.ItemMenu;

import android.graphics.Bitmap;

public class ItemMenuStructure {
    private String foodname;
    private String urlimg;
    private int quantity;
    private Boolean deleteui;
    private String id;
    private Bitmap img;

    public ItemMenuStructure(String foodname, String urlimg, int quantity, String id) {
        this.foodname = foodname;
        this.urlimg = urlimg;
        this.quantity = quantity;
        this.id = id;
    }
    public void setImg(Bitmap img) {
        this.img = img;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setDeleteui(Boolean deleteui) {
        this.deleteui = deleteui;
    }
    public Boolean getDeleteui() {
        return deleteui;
    }
    public Bitmap getImg() {
        return img;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getFoodname() {
        return foodname;
    }
    public String getUrlimg() {
        return urlimg;
    }
    public String getId() {
        return id;
    }
}

