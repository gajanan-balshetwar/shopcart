package com.shoppingcart.ui.Products;

import com.shoppingcart.data.network.models.Product;

/**
 * Created by Gajanan on 29-09-2017.
 */

public interface ProductPresenter {

    void afterViewStarted();

    void onAddToCartClick(Product product);
}
