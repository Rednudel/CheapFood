package com.example.david.cheapfood.Offer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.david.cheapfood.R;

import java.util.List;

/**
 * Created by David on 09.05.2017.
 */

public class OfferListAdapter extends BaseAdapter{

    private Context mContext;
    private List<Offer> mOfferList;

    //Consturctor
    public OfferListAdapter (Context mContext, List<Offer> mOfferList){
        this.mContext = mContext;
        this.mOfferList = mOfferList;
    }

    @Override
    public int getCount() {
        return mOfferList.size();
    }

    @Override
    public Object getItem(int position) {
        return mOfferList.get(position);
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
        TextView tvDescription = (TextView)v.findViewById(R.id.tv_description);
        TextView tvAddress = (TextView)v.findViewById(R.id.tv_address);
        TextView tvContingent = (TextView)v.findViewById(R.id.tv_contingent);
        //Set text for TextView
        tvName.setText(mOfferList.get(position).getName());
        tvPrice.setText(String.valueOf(mOfferList.get(position).getPrice())+ " €");
        tvDescription.setText(mOfferList.get(position).getDescription());
        tvAddress.setText(mOfferList.get(position).getAddress());
        tvContingent.setText("Kontingent = " + mOfferList.get(position).getContigent());

        //Save product id to tag
        v.setTag(mOfferList.get(position).getId());
        return v;
    }
}
