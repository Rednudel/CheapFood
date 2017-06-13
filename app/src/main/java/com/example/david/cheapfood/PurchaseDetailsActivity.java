package com.example.david.cheapfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Julian on 29.05.2017.
 */

public class PurchaseDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Get the intent that started us to find the parameter (extra)
        Intent toy = getIntent();

        String name = toy.getStringExtra("name");
        double price = toy.getDoubleExtra("price", 0);
        long quantity = toy.getLongExtra("quantity", 0);
        // String orderDate = toy.getStringExtra("orderDate");
        double totalPrice = toy.getDoubleExtra("total price", 0);

        TextView tvDetails = (TextView) findViewById(R.id.tv_Details);
        tvDetails.setText("Details");

        TextView tvPurchaseDetails = (TextView) findViewById(R.id.tv_purchaseDetails);
        tvPurchaseDetails.setText(name);

        TextView tvPrice = (TextView) findViewById(R.id.tv_purchasePrice);
        tvPrice.setText("Preis " + price + " Euro");

        TextView tvQuantity = (TextView) findViewById(R.id.tv_purchaseQuantity);
        tvQuantity.setText("Menge: " + quantity + " Stück");

        TextView tvTotalPrice = (TextView)findViewById(R.id.tv_purchaseTotalPrice);
        tvTotalPrice.setText("Gesamtpreis: " + totalPrice + " Euro");

        /*TextView tvOrderDate = (TextView) findViewById(R.id.tv_purchaseOrderDate);
        tvOrderDate.setText(orderDate);*/

    }

    @Override
    public void onClick(View v) {
        buttonBack = (Button) findViewById(R.id.btn_back);

        buttonBack.setOnClickListener(this);

        Intent intent = new Intent(this, PurchaseHistoryActivity.class);

        startActivity(intent);
    }
}
