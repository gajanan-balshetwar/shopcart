package com.shoppingcart.ui.cart;

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
import com.shoppingcart.data.db.models.ProductInCartModel;
import com.shoppingcart.data.network.models.Product;
import com.shoppingcart.ui.Products.ProductsFragment;

import java.util.List;


public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder> {

    private final List<ProductInCartModel> productList;
    private final CartFragment.OnFragmentInteractionListener mListener;

    public CartRecyclerViewAdapter(List<ProductInCartModel> items, CartFragment.OnFragmentInteractionListener listener) {
        productList = items;
        mListener = listener;
    }

    public void changeProductList(List<ProductInCartModel> productList){
        if(productList != null) {
            this.productList.clear();
            this.productList.addAll(productList);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
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

    public void removeFromPosition(int position) {

        productList.remove(position);
        notifyItemRemoved(position);
    }

    public String getTotal() {

        float total = 0;
        for(ProductInCartModel cartModel : productList){
            total += cartModel.getTotal();
        }

        return "Total :" + Float.toString(total);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private ImageView imgProductThumb;
        private TextView txtProductName;
        private TextView txtProductAmount;
        private TextView txtVendorName;
        private TextView txtVendorAddress;
        private TextView txtQuantity;
        private Button btnRemoveFromCart;
        private Button btnCallVendor;
        private ProductInCartModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            imgProductThumb = (ImageView) itemView.findViewById(R.id.img_product_image);
            txtProductName = (TextView) itemView.findViewById(R.id.txt_pname);
            txtProductAmount = (TextView) itemView.findViewById(R.id.txt_pamount);
            txtVendorName = (TextView) itemView.findViewById(R.id.txt_vname);
            txtVendorAddress = (TextView) itemView.findViewById(R.id.txt_vaddress);
            btnRemoveFromCart = (Button) itemView.findViewById(R.id.btn_remove_from_cart);
            btnCallVendor = (Button) itemView.findViewById(R.id.btn_call_vendor);
            txtQuantity = itemView.findViewById(R.id.txt_quantity);

            btnRemoveFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onRemoveFromCart(getAdapterPosition(), mItem);
                    }
                }
            });

            btnCallVendor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (null != mListener) {
                        mListener.onVendorCall(mItem);
                    }
                }
            });
        }



        public void setData(){
            txtProductAmount.setText("Price : "+mItem.getPrice());
            txtProductName.setText(mItem.getName());
            txtVendorAddress.setText(mItem.getVendoraddress());
            txtVendorName.setText(mItem.getVendorname());
            txtQuantity.setText("Quantity : " + mItem.getQuantity());

            ApplicationController.getInstance().getApiCallService().getImageLoader().get(mItem.getProductImg(), ImageLoader.getImageListener(imgProductThumb, R.drawable.product, R.drawable.product));
        }
    }


}
