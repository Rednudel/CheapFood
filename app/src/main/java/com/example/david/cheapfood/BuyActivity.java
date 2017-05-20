package com.example.david.cheapfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import static com.example.david.cheapfood.R.id.bt_buy;


public class BuyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        //Get the intent that started us to find the parameter (extra)
        Intent toy = getIntent();
        int id = toy.getIntExtra("id", 0);
        double price = toy.getDoubleExtra("price", 0);
        long contigent = toy.getLongExtra("contigent",0);
        String name = toy.getStringExtra("name");
        String address = toy.getStringExtra("address");
        String description = toy.getStringExtra("description");

        //Display the value to the screen.
        TextView tvcontigent = (TextView) findViewById(R.id.tv_contingent);
        tvcontigent.setText("Es sind noch "+contigent + " Einheiten vorhanden");

        TextView tvprice = (TextView) findViewById(R.id.tv_price);
        tvprice.setText(price+" Euro");

        TextView tvname = (TextView) findViewById(R.id.tv_name);
        tvname.setText(name.toString());

        TextView tvaddress = (TextView) findViewById(R.id.tv_address);
        tvaddress.setText(address);

        TextView tvdescription = (TextView) findViewById(R.id.tv_description);
        tvdescription.setText(description);

        Button btbuy = (Button) findViewById(bt_buy);
        btbuy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BuyActivity.this,PaypalBuyActivity.class);
                startActivity(i);
            }
        });
    }

    /*public void openPaypal(View view){
        Intent intent = new Intent(BuyActivity.this, PaypalBuyActivity.class);
        startActivity(intent);
    }
    */

}
