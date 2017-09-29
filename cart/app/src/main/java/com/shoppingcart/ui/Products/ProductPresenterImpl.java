package com.shoppingcart.ui.Products;

import com.shoppingcart.data.network.ProductApiCall;
import com.shoppingcart.data.network.models.Product;

import java.util.List;

/**
 * Created by Gajanan on 29-09-2017.
 */

public class ProductPresenterImpl implements ProductPresenter {

    private ProductView productView;
    private List<Product> productList;
    private ProductApiCall apiCall = new ProductApiCall();
    private ProductApiCall.ProductApiResponse apiResponse = new ProductApiCallListenerImpl();

    ProductPresenterImpl(ProductView productView){
        this.productView = productView;
    }


    @Override
    public void afterViewStarted() {

        if(productList == null || productList.size() == 0){
            productView.showProgressDialog();
        }
        apiCall.getProducts(apiResponse);
    }

    @Override
    public void onAddToCartClick(Product product) {

    }

    private class ProductApiCallListenerImpl implements ProductApiCall.ProductApiResponse{

        @Override
        public void onSuccess(List<Product> products) {
            productView.hideProgressDialog();
            if(!productList.equals(productList)){
                productList = products;
                productView.showTheProductList(productList);
            }
        }

        @Override
        public void onError(int statusCode, String errorMessage) {
            productView.hideProgressDialog();
            if(productList == null || productList.size() == 0) {
                productView.showErrorDialog(errorMessage);
                productView.showErrorEmptyList();
            }
        }
    }
}
