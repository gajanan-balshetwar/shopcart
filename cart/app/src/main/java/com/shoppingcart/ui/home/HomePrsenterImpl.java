package com.shoppingcart.ui.home;

/**
 * Created by Gajanan on 29-09-2017.
 */

public class HomePrsenterImpl implements HomePresenter {

    private HomeView homeView;

    HomePrsenterImpl(HomeView homeView){
        this.homeView = homeView;
    }


    @Override
    public void afterViewCreated() {
        openProductScreen();
    }

    private void openProductScreen() {
        homeView.setViewTitle("Shop");
        homeView.openProductScreen();
    }

    @Override
    public void onProductOptionSelected() {
        openProductScreen();
    }

    @Override
    public void onCartOptionSelected() {
        openCartScreen();
    }

    private void openCartScreen() {
        homeView.setViewTitle("Products");
        homeView.openCartScreen();
    }
}
