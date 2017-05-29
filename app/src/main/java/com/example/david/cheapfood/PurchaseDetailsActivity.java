package com.example.david.cheapfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by Julian on 29.05.2017.
 */

public class PurchaseDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Get the intent that started us to find the parameter (extra)
        Intent toy = getIntent();

        String name = toy.getStringExtra("name");
        double price = toy.getDoubleExtra("price", 0);
        long quantity = toy.getLongExtra("quantity", 0);
        // Date orderDate = toy.("orderDate");

        TextView tvName = (TextView) findViewById(R.id.tv_purchaseName);
        tvName.setText(name);

        TextView tvPrice = (TextView) findViewById(R.id.tv_purchasePrice);
        tvPrice.setText(price + " Euro");

        TextView tvQuantity = (TextView) findViewById(R.id.tv_purchaseQuantity);
        tvQuantity.setText(quantity + " Stück");

        /*TextView tvOrderDate = (TextView) findViewById(R.id.tv_orderDate);
        tvOrderDate.setText(orderDate);*/

    }
}
