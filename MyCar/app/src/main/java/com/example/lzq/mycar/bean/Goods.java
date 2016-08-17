package com.example.lzq.mycar.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by LYF on 2016/6/20.
 */
public class Goods extends DataSupport {
    private int id;
    private String name;
    private String picture;
    private String dec;
    private boolean isChecked;
    private String total;
    private String commodNumber;//商品数量


    public Goods(String commodNumber, int id, String name, String picture, String dec/*, boolean isChecked*/, String total) {
        this.commodNumber = commodNumber;
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.dec = dec;
        this.isChecked = isChecked;
        this.total = total;
    }

    public String getCommodNumber() {
        return commodNumber;
    }

    public void setCommodNumber(String commodNumber) {
        this.commodNumber = commodNumber;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }
}
