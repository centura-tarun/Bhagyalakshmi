package com.sourceedge.bhagyalakshmi.orders.orders.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Centura User1 on 06-12-2016.
 */

public class SalesPersonOrdersAdapter extends RecyclerView.Adapter<SalesPersonOrdersAdapter.ViewHolder> {
    Context mContext;
    public SalesPersonOrdersAdapter(Context context){
        this.mContext=context;
    }
    @Override
    public SalesPersonOrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SalesPersonOrdersAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
