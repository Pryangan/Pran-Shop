package com.pryangan.pranstore;

/**
 * Created by pryangan on 9/10/17.
 */

public class Product {
    boolean isSelected;
    String userName;

    private int imageId;
    private String title;
    private String description;
    private String type;
    private String cost;
    private String mfg;
    private String exp;
    private String com;

    public Product(int imageId,String title,String description,String type,String cost,String mfg,String exp,String com)
    {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.cost = cost;
        this.mfg = mfg;
        this.exp = exp;
        this.com = com;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getCost() {
        return cost;
    }

    public String getCom() {
        return com;
    }

    public String getExp() {
        return exp;
    }

    public String getMfg() {
        return mfg;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
