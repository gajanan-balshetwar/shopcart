package com.shoppingcart.ui.splash;


import android.os.Handler;

/**
 * Created by Gajanan on 29-09-2017.
 */

public class SplashPresenterImpl implements SplashPresenter {

    private static final int WAIT_IN_MILIS = 5000;
    private final SplashView splashView;

    SplashPresenterImpl(SplashView splashView){
        this.splashView = splashView;
    }


    private Runnable nextActivity = new Runnable() {
        @Override
        public void run() {
            splashView.openHomeActivity();
        }
    };

    @Override
    public void afterStarted() {

        Handler handler = new Handler();
        handler.postDelayed(nextActivity, WAIT_IN_MILIS);

    }
}
