package com.shoppingcart;

import android.app.Application;

import com.orm.SugarApp;
import com.shoppingcart.data.network.ApiCallService;

/**
 * Created by Gajanan on 29-09-2017.
 */

public class ApplicationController extends SugarApp {

    private static ApplicationController instance;

    private ApiCallService apiCallService;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        apiCallService = new ApiCallService(this);
    }

    public static ApplicationController getInstance(){
        return  instance;
    }

    public ApiCallService getApiCallService(){
        return apiCallService;
    }

}
