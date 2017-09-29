package com.shoppingcart.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.shoppingcart.R;

public class HomeActivity extends AppCompatActivity implements HomeView {

    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter = new HomePrsenterImpl(this);
        presenter.afterViewCreated();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_product:
                    presenter.onProductOptionSelected();
                    return true;
                case R.id.navigation_cart:
                    presenter.onCartOptionSelected();
                    return true;
            }
            return false;
        }

    };

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }

    @Override
    public void setViewTitle(String title) {
        setTitle(title);
    }

    @Override
    public void openProductScreen() {

    }

    @Override
    public void openCartScreen() {

    }
}