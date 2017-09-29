package com.shoppingcart.data.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Gajanan on 28-09-2017.
 */

public class Product {

    @SerializedName("productname")
    private String name;

    private float price;

    private String vendorname;

    private String vendoraddress;

    private String productImg;

    private String phoneNumber;

    private List<String> productGallery;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getVendoraddress() {
        return vendoraddress;
    }

    public void setVendoraddress(String vendoraddress) {
        this.vendoraddress = vendoraddress;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getProductGallery() {
        return productGallery;
    }

    public void setProductGallery(List<String> productGallery) {
        this.productGallery = productGallery;
    }

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof Product)){
            return false;
        }

        Product other = (Product)obj;
        return other.name.equals(name);
    }
}
