package com.example.david.cheapfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvOffer;
    private OfferListAdapter adapter;
    public List<Offer> mOfferList;
    private OffersDataSource dataSource;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        dataSource = new OffersDataSource(this);
        lvOffer = (ListView)findViewById(R.id.listview_offer);

        Log.d(LOG_TAG, "open database.");
        dataSource.open();

        //create a few offers
        Offer offer1 = dataSource.createOffer(new Offer(1,"K&U Brötchen", 0.2, "Brötchen vom Vortag","Edeka Ooser Hauptstr.6 76473 Iffezheim",10));
        Offer offer2 = dataSource.createOffer(new Offer(1,"K&U Brötchen", 0.2, "Brötchen vom Vortag","Edeka Ooser Hauptstr.6 76473 Iffezheim",10));
        Offer offer3 = dataSource.createOffer(new Offer(1,"K&U Brötchen", 0.2, "Brötchen vom Vortag","Edeka Ooser Hauptstr.6 76473 Iffezheim",10));

        showAllListEntries();

        //mOfferList = dataSource.getAllOffers();
        //Init adapter
        //adapter = new OfferListAdapter(getApplicationContext(),mOfferList);
        //lvOffer.setAdapter(adapter);

        lvOffer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //Do something
                double price = mOfferList.get(position).getPrice();
                long contigent = mOfferList.get(position).getContigent();
                String description = mOfferList.get(position).getDescription();
                String name = mOfferList.get(position).getName();
                String adress = mOfferList.get(position).getAddress();

                //Ex. display msg with product id get from view.getTag
                Toast.makeText(getApplicationContext(), "Clicked product id =" +view.getTag(), Toast.LENGTH_SHORT).show();

                //Call the next Activity
                Intent toy = new Intent(MainActivity.this, BuyActivity.class);
                toy.putExtra("price", price);

                toy.putExtra("contigent", contigent);
                toy.putExtra("description", description);
                toy.putExtra("name", name);
                toy.putExtra("address", adress);
                toy.putExtra("position", position);

                startActivity(toy);
            }
        });

        Log.d(LOG_TAG, "close database.");
        dataSource.close();
    }

    private void showAllListEntries () {
        mOfferList = dataSource.getAllOffers();

        adapter = new OfferListAdapter(getApplicationContext(),mOfferList);
        lvOffer.setAdapter(adapter);

        /*
        ArrayAdapter<Offer> offerArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                mOfferList);

        lvOffer.setAdapter(offerArrayAdapter);
        */
    }

}
