package com.shoppingcart.ui.cart;

import com.shoppingcart.data.db.models.ProductInCartModel;

/**
 * Created by Gajanan on 29-09-2017.
 */

public interface CartPresenter {

    void afterViewStarted();

    void callToVender(String vendorNumber);

    void removeFromCart(int position, ProductInCartModel model);

}
