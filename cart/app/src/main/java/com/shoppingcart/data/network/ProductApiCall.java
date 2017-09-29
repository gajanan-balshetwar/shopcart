package com.shoppingcart.data.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.shoppingcart.ApplicationController;
import com.shoppingcart.data.network.models.Product;
import com.shoppingcart.data.network.models.ProductApiResponseModel;

import java.util.List;

/**
 * Created by Gajanan on 28-09-2017.
 */

public class ProductApiCall {


    public void getProducts(final ProductApiResponse productApiResponse){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiEndPoints.PRODUCTS_ENDPOINT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(productApiResponse != null){
                    Log.d("", response);
                    ProductApiResponseModel apiResponse = (new Gson()).fromJson(response, ProductApiResponseModel.class);
                    if(apiResponse != null && apiResponse.getProducts() != null && apiResponse.getProducts().size() > 0){
                        productApiResponse.onSuccess(apiResponse.getProducts());
                    }else {
                        productApiResponse.onError(55, "Something went wrong. please try again");
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("", error.getMessage());
                if(error != null && error.networkResponse != null) {
                    productApiResponse.onError(error.networkResponse.statusCode, "Something went wrong. please try again");
                }else{
                    productApiResponse.onError(10, "Something went wrong. please try again");
                }
            }
        });

        ApplicationController.getInstance().getApiCallService().addToRequestQueue(stringRequest);
    }

    static public interface ProductApiResponse{

        public void onSuccess(List<Product> products);

        public void onError(int statusCode, String errorMessage);

    }

}
