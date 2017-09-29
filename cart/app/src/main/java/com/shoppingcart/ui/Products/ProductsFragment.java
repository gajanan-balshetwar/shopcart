package com.shoppingcart.ui.Products;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoppingcart.R;
import com.shoppingcart.data.network.models.Product;
import com.shoppingcart.util.CommonUtil;

import java.util.List;


public class ProductsFragment extends Fragment implements ProductView{

    private OnFragmentInteractionListener mListener;
    private ProgressDialog progressDialog;
    private ProductPresenter presenter;
    private static ProductsFragment instance;

    public ProductsFragment() {
        // Required empty public constructor

        presenter = new ProductPresenterImpl(this);
    }


    public static ProductsFragment getInstance() {
        if(instance == null) {
            instance = new ProductsFragment();
            Bundle args = new Bundle();
            instance.setArguments(args);
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_products, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgressDialog() {

        hideProgressDialog();
        progressDialog = CommonUtil.showLoadingDialog(this.getContext());
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    @Override
    public void showErrorNetworkDialog() {


    }

    @Override
    public void showErrorDialog(String message) {

    }

    @Override
    public void showErrorEmptyList() {

    }

    @Override
    public void showTheProductList(List<Product> products) {

    }

    public interface OnFragmentInteractionListener {

    }
}
