package com.shoppingcart.ui.cart;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shoppingcart.R;
import com.shoppingcart.data.db.models.ProductInCartModel;
import com.shoppingcart.data.network.models.Product;
import com.shoppingcart.ui.Products.ProductsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements CartView{

    private static CartFragment instance;
    private RecyclerView rvProductList;
    private CartRecyclerViewAdapter adapter;
    private TextView txtTotal;
    private TextView txtEmpty;

    private OnFragmentInteractionListener mListener;

    private CartPresenter presenter;
    public CartFragment() {
        presenter = new CartPresenterImpl(this);
    }

    public static CartFragment getInstance() {


        if(instance == null){
            instance = new CartFragment();
        }

        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        rvProductList = view.findViewById(R.id.list);
        rvProductList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new CartRecyclerViewAdapter(new ArrayList<ProductInCartModel>(), this.mListener);
        rvProductList.setAdapter(adapter);
        txtTotal = view.findViewById(R.id.txt_total);
        txtEmpty = view.findViewById(R.id.txt_empty_error);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.afterViewStarted();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = new OnFragmentInteractionListener() {
            @Override
            public void onRemoveFromCart(int postion, ProductInCartModel model) {
                presenter.removeFromCart(postion, model);
            }

            @Override
            public void onVendorCall(ProductInCartModel model) {
                presenter.callToVender(model.getPhoneNumber());
            }
        };

    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    @Override
    public void showEmptyCartError() {
            // txt visible and //rv inVisible

        rvProductList.setVisibility(View.GONE);
        txtTotal.setVisibility(View.GONE);
        txtEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCartProductList(List<ProductInCartModel> productInCartModels) {
        rvProductList.setVisibility(View.VISIBLE);
        txtEmpty.setVisibility(View.GONE);
        adapter.changeProductList(productInCartModels);
    }

    @Override
    public void showRemoveConfirmationDialog(ProductInCartModel cartModel, final DialogConfirmationResponseListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext())
                .setTitle("Alert")
                .setMessage("Do you want to remove " + cartModel.getName() + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.afterConfirmation();
                    }
                })
                .setNegativeButton("No", null);
        builder.show();
    }

    @Override
    public void showTotal(String total) {
        txtTotal.setVisibility(View.VISIBLE);
        txtTotal.setText(total);
    }

    @Override
    public void removeFromList(int position) {

        adapter.removeFromPosition(position);

        if(adapter.getItemCount() == 0){
            showEmptyCartError();
        }
    }

    @Override
    public String getTotal() {
        return adapter.getTotal();
    }

    @Override
    public void callToVendor(String phoneNumber) {
        try {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
        } catch (SecurityException e) {
        }
    }

    public interface OnFragmentInteractionListener {

        void onRemoveFromCart(int postion, ProductInCartModel model);

        void onVendorCall(ProductInCartModel model);
    }
}
