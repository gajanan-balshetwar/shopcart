package com.shoppingcart.ui.Products;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.shoppingcart.ApplicationController;
import com.shoppingcart.R;
import com.shoppingcart.data.network.models.Product;

import java.util.List;


public class ProductsRecyclerViewAdapter extends RecyclerView.Adapter<ProductsRecyclerViewAdapter.ViewHolder> {

    private final List<Product> productList;
    private final ProductsFragment.OnFragmentInteractionListener mListener;

    public ProductsRecyclerViewAdapter(List<Product> items, ProductsFragment.OnFragmentInteractionListener listener) {
        productList = items;
        mListener = listener;
    }

    public void changeProductList(List<Product> productList){
        if(productList != null) {
            this.productList.clear();
            this.productList.addAll(productList);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = productList.get(position);
        holder.setData();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final ImageView imgProductThumb;
        private final TextView txtProductName;
        private final TextView txtProductAmount;
        private final TextView txtVendorName;
        private final TextView txtVendorAddress;
        private final Button btnAddtoCart;
        private Product mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            imgProductThumb = (ImageView) itemView.findViewById(R.id.img_product_image);
            txtProductName = (TextView) itemView.findViewById(R.id.txt_pname);
            txtProductAmount = (TextView) itemView.findViewById(R.id.txt_pamount);
            txtVendorName = (TextView) itemView.findViewById(R.id.txt_vname);
            txtVendorAddress = (TextView) itemView.findViewById(R.id.txt_vaddress);
            btnAddtoCart = (Button) itemView.findViewById(R.id.btn_add_cart);


            btnAddtoCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onAddToCartClick(mItem);
                    }
                }
            });
        }

        public void setData(){
            txtProductAmount.setText("Price : "+mItem.getPrice());
            txtProductName.setText(mItem.getName());
            txtVendorAddress.setText(mItem.getVendoraddress());
            txtVendorName.setText(mItem.getVendorname());

            ApplicationController.getInstance().getApiCallService().getImageLoader().get(mItem.getProductImg(), ImageLoader.getImageListener(imgProductThumb, R.drawable.product, R.drawable.product));
        }
    }


}
