package com.shoppingcart.ui.splash;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shoppingcart.R;
import com.shoppingcart.ui.home.HomeActivity;

public class SplashActivity extends AppCompatActivity implements SplashView {

    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        presenter = new SplashPresenterImpl(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.afterStarted();
    }

    @Override
    public void openHomeActivity() {
        Intent intent = HomeActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }
}
