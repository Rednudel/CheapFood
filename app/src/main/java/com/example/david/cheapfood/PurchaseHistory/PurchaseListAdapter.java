package com.example.david.cheapfood.PurchaseHistory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.david.cheapfood.R;

import java.util.List;

/**
 * Created by Julian on 29.05.2017.
 */

public class PurchaseListAdapter extends BaseAdapter{

    private Context mContext;
    private List<PurchaseHistory> mPurchaseList;

    //Consturctor
    public PurchaseListAdapter (Context mContext, List<PurchaseHistory> mPurchaseList){
        this.mContext = mContext;
        this.mPurchaseList = mPurchaseList;
    }

    @Override
    public int getCount() {
        return mPurchaseList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPurchaseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_offer_list, null);
        TextView tvName = (TextView)v.findViewById(R.id.tv_name);
        TextView tvPrice = (TextView)v.findViewById(R.id.tv_price);
        // TextView tvQuantity = (TextView)v.findViewById(R.id.tv_quantity);
        TextView tvAddress = (TextView)v.findViewById(R.id.tv_address);
        //Set text for TextView
        tvName.setText(mPurchaseList.get(position).getOfferName());
        tvPrice.setText(String.valueOf(mPurchaseList.get(position).getOfferPrice())+ " €");
        // tvQuantity.setText(String.valueOf(mPurchaseList.get(position).getQuantity()));
        tvAddress.setText(String.valueOf(mPurchaseList.get(position).getOrderDate()));

        //Save product id to tag
        v.setTag(mPurchaseList.get(position).getId());
        return v;
    }
}