package com.shoppingcart.ui.cart;

import com.shoppingcart.data.db.models.ProductInCartModel;
import com.shoppingcart.data.network.models.Product;

import java.util.List;

/**
 * Created by Gajanan on 29-09-2017.
 */

public interface CartView {

    void showEmptyCartError();

    void showCartProductList(List<ProductInCartModel> productInCartModels);

    void showTotal(String total);

    void showRemoveConfirmationDialog(ProductInCartModel cartModel, DialogConfirmationResponseListener listener);

    void removeFromList(int position);

    void callToVendor(String number);

    String getTotal();
}

 interface DialogConfirmationResponseListener{

     void afterConfirmation();
}
