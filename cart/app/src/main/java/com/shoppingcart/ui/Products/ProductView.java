package com.shoppingcart.ui.Products;

import android.content.Context;

import com.shoppingcart.data.network.models.Product;

import java.util.List;

/**
 * Created by Gajanan on 29-09-2017.
 */

public interface ProductView {

    void showProgressDialog();

    void hideProgressDialog();

    void showErrorNetworkDialog();

    void showErrorDialog(String errorMessage);

    void showErrorEmptyList();

    void showTheProductList(List<Product> products);

    void showToast(String message);
}
