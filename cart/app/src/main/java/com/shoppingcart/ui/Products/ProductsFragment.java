package com.shoppingcart.ui.Products;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shoppingcart.R;
import com.shoppingcart.data.network.models.Product;
import com.shoppingcart.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;


public class ProductsFragment extends Fragment implements ProductView{

    public static final String TAG = ProductsFragment.class.getSimpleName() ;
    private OnFragmentInteractionListener mListener;
    private ProgressDialog progressDialog;
    private ProductPresenter presenter;
    private static ProductsFragment instance;

    private RecyclerView rvProductList;
    private ProductsRecyclerViewAdapter adapter;

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

        View view = inflater.inflate(R.layout.fragment_products, container, false);
        rvProductList = view.findViewById(R.id.list);
        rvProductList.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        adapter = new ProductsRecyclerViewAdapter(new ArrayList<Product>(), this.mListener);
        rvProductList.setAdapter(adapter);
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
                public void onAddToCartClick(Product product) {
                    presenter.onAddToCartClick(product);
                }
            };

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
        adapter.changeProductList(products);
    }

    public interface OnFragmentInteractionListener {

        void onAddToCartClick(Product product);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
