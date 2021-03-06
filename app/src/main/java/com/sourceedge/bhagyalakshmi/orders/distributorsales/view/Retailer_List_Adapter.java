package com.sourceedge.bhagyalakshmi.orders.distributorsales.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sourceedge.bhagyalakshmi.orders.R;
import com.sourceedge.bhagyalakshmi.orders.distributorsales.controller.Retailer_Lookup;
import com.sourceedge.bhagyalakshmi.orders.models.Role;
import com.sourceedge.bhagyalakshmi.orders.support.Class_Static;

import java.util.ArrayList;

/**
 * Created by Centura User1 on 10-12-2016.
 */

public class Retailer_List_Adapter extends RecyclerView.Adapter<Retailer_List_Adapter.ViewHolder>{

    Context mContext;
    ArrayList<Role> data;
    public Retailer_List_Adapter(Context context,ArrayList<Role> model){
        this.mContext=context;
        this.data=model;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_retailer_list,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.text.setText(data.get(position).getName().toString());
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class_Static.tempRole=new Role();
                Class_Static.tempRole = data.get(position);
                Retailer_Lookup.retailerSearch.setText(Class_Static.tempRole.getName());
                Retailer_Lookup.retailerList.setVisibility(View.GONE);
                Retailer_Lookup.orderProductRecyclerview.setVisibility(View.GONE);

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
            text=(TextView)itemView.findViewById(R.id.text1);
        }
    }
}