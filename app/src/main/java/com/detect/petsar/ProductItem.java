package com.detect.petsar;

public class ProductItem {
    private int img; // 제품사진
    private String title; // 제품명
    private String price; // 제품가격

    public ProductItem(int img, String title, String price){
        this.img = img;
        this.title = title;
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}
