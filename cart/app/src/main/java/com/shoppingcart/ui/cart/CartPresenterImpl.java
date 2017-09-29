package com.shoppingcart.ui.cart;

import com.shoppingcart.data.db.models.ProductInCartModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gajanan on 29-09-2017.
 */

public class CartPresenterImpl implements CartPresenter {

    private final CartView cartView;

//    private List<ProductInCartModel> productInCartList = new ArrayList<ProductInCartModel>();

    CartPresenterImpl(CartView cartView){
        this.cartView = cartView;
    }

    @Override
    public void afterViewStarted() {

        cartView.showEmptyCartError();

        List<ProductInCartModel> productInCartList = ProductInCartModel.getAllProductInCart();
        if(productInCartList != null && productInCartList.size() > 0) {
            cartView.showCartProductList(productInCartList);

            cartView.showTotal(cartView.getTotal());
        }
    }



    @Override
    public void callToVender(String vendorNumber) {
        cartView.callToVendor(vendorNumber);
    }

    @Override
    public void removeFromCart(final int position, final ProductInCartModel model) {
       cartView.showRemoveConfirmationDialog(model, new DialogConfirmationResponseListener() {
           @Override
           public void afterConfirmation() {
               model.delete();
               cartView.removeFromList(position);
               cartView.showTotal(cartView.getTotal());
           }
       });
    }
}
