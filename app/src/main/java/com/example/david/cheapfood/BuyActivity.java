package com.example.david.cheapfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class BuyActivity extends AppCompatActivity implements View.OnClickListener {


    private String paymentAmount;
    private Button buttonPay;
    private Double amount;

    public static final int PAYPAL_REQUEST_CODE = 123;

    //Paypal Configuration Object
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        //Get the intent that started us to find the parameter (extra)
        Intent toy = getIntent();
        int id = toy.getIntExtra("id", 0);
        double price = toy.getDoubleExtra("price", 0);
        long contigent = toy.getLongExtra("contigent", 0);
        String name = toy.getStringExtra("name");
        String address = toy.getStringExtra("address");
        String description = toy.getStringExtra("description");

        //Display the value to the screen.
        TextView tvcontigent = (TextView) findViewById(R.id.tv_contingent);
        tvcontigent.setText("Es sind noch " + contigent + " Einheiten vorhanden");

        TextView tvprice = (TextView) findViewById(R.id.tv_price);
        tvprice.setText(price + " Euro");

        TextView tvname = (TextView) findViewById(R.id.tv_name);
        tvname.setText(name.toString());

        TextView tvaddress = (TextView) findViewById(R.id.tv_address);
        tvaddress.setText(address);

        TextView tvdescription = (TextView) findViewById(R.id.tv_description);
        tvdescription.setText(description);

        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        ArrayList<Long> list = new ArrayList<>();
        long i = 1;
        while ( i <= contigent ) {
            list.add(i);
            i++;
        }

        ArrayAdapter<Long> dataAdapter = new ArrayAdapter<Long>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(dataAdapter);

    }


    @Override
    public void onClick(View v) {

        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        Intent toy = getIntent();
        buttonPay = (Button) findViewById(R.id.bt_buy);
        amount = toy.getDoubleExtra("price", 0) *  Double.parseDouble(dropdown.getSelectedItem().toString());

        buttonPay.setOnClickListener(this);

        Intent intent = new Intent(this, PayPalService.class);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        startService(intent);

        getPayment();
    }

    //Paypal intent request code to track onActivityResult method

    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    private void getPayment() {
        //Getting the amount from editText
        paymentAmount = amount.toString();

        //Creating a paypalpayment
        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(paymentAmount)), "EUR", "CheapFood",
                PayPalPayment.PAYMENT_INTENT_SALE);

        //Creating Paypal Payment activity intent
        Intent intent = new Intent(this, PaymentActivity.class);

        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If the result is from paypal
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Log.i("paymentExample", paymentDetails);

                        //Starting a new activity for the payment details and also putting the payment details with intent
                        startActivity(new Intent(this, ConfirmationActivity.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", paymentAmount));

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    /*public void openPaypal(View view){
        Intent intent = new Intent(BuyActivity.this, PaypalBuyActivity.class);
        startActivity(intent);
    }
    */

    }
}
