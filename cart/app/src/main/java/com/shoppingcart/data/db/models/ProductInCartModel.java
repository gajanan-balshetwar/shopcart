package com.shoppingcart.data.db.models;

import android.database.sqlite.SQLiteException;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.shoppingcart.data.network.models.Product;

import java.util.List;

/**
 * Created by Gajanan on 29-09-2017.
 */

public class ProductInCartModel extends SugarRecord<ProductInCartModel> {

    @SerializedName("productname")
    private String name;

    private float price;

    private String vendorname;

    private String vendoraddress;

    private String productImg;

    private String phoneNumber;

    private List<String> productGallery;

    private int quantity;

    public ProductInCartModel(){

    }

    public  ProductInCartModel(Product product){
        this.name = product.getName();
        this.price = product.getPrice();
        this.vendorname = product.getVendorname();
        this.vendoraddress = product.getVendoraddress();
        this.productImg = product.getProductImg();
        this.phoneNumber = product.getPhoneNumber();
        this.productGallery = product.getProductGallery();
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal(){
        return  quantity * price;
    }

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof ProductInCartModel)){
            return false;
        }

        ProductInCartModel other = (ProductInCartModel)obj;
        return other.name.equals(name);
    }

    public static ProductInCartModel getCartModel(String name){
        try {
            List<ProductInCartModel> cartModelList = ProductInCartModel.find(ProductInCartModel.class, "name= '" + name + "'");

            if(cartModelList != null && cartModelList.size() > 0){
               return cartModelList.get(0);
            }

        }catch (SQLiteException se){
            return  null;
        }

        return null;
    }

    public static List<ProductInCartModel> getAllProductInCart(){
        try{

           return ProductInCartModel.listAll(ProductInCartModel.class);
        }catch (Exception e){

        }

        return  null;
    }
}
