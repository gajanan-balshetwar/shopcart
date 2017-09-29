package com.shoppingcart.data.network.models;

import java.util.List;

/**
 * Created by Gajanan on 28-09-2017.
 */

public class ProductApiResponseModel {

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
