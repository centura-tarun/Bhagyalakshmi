package com.sourceedge.bhagyalakshmi.orders.distributorsales.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sourceedge.bhagyalakshmi.orders.R;
import com.sourceedge.bhagyalakshmi.orders.distributorsales.controller.Distributor_Sales;
import com.sourceedge.bhagyalakshmi.orders.distributorsales.controller.Retailer_Lookup;
import com.sourceedge.bhagyalakshmi.orders.models.Product;
import com.sourceedge.bhagyalakshmi.orders.models.Role;
import com.sourceedge.bhagyalakshmi.orders.support.Class_Static;

import java.util.ArrayList;

/**
 * Created by Centura User1 on 12-12-2016.
 */

public class Product_List_Adapter extends RecyclerView.Adapter<Product_List_Adapter.ViewHolder> {
    Context mContext;
    ArrayList<Product> data;

    public Product_List_Adapter(Context context, ArrayList<Product> model) {
        this.mContext = context;
        this.data = model;
    }

    @Override
    public Product_List_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_retailer_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(Product_List_Adapter.ViewHolder holder, final int position) {
        holder.text.setText(data.get(position).getName().toString());
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class_Static.tempProduct=new Product();
                Class_Static.tempProduct = data.get(position);
                Class_Static.tempProduct.setQuantity(1);
                Distributor_Sales.productSearch.setText(Class_Static.tempProduct.getName());
                Distributor_Sales.productBrand.setText(Class_Static.tempProduct.getBrand());
                Distributor_Sales.productCategory.setText(Class_Static.tempProduct.getCategory());
                Distributor_Sales.productDescription.setText(Class_Static.tempProduct.getDescription());
                Distributor_Sales.productUnit.setText(Class_Static.tempProduct.getUnits());
                Distributor_Sales.productQuantity.setText(Class_Static.tempProduct.getQuantity() + "");
                Distributor_Sales.productPrice.setText(Class_Static.tempProduct.getPrice() + "");
                Distributor_Sales.productList.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text1);

        }
    }
}
