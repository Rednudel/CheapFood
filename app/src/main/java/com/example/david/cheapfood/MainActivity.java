package com.example.david.cheapfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvOffer;
    private OfferListAdapter adapter;
    public List<Offer> mOfferList;

    static public Offer offer1 = new Offer(1,"K&U Brötchen", 0.2, "Brötchen vom Vortag","Edeka Ooser Hauptstr.6 76473 Iffezheim",10);
    static public Offer offer2 = new Offer(1,"K&U Brötchen", 0.2, "Brötchen vom Vortag","Edeka Ooser Hauptstr.6 76473 Iffezheim",10);
    static public Offer offer3 = new Offer(1,"K&U Brötchen", 0.2, "Brötchen vom Vortag","Edeka Ooser Hauptstr.6 76473 Iffezheim",10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvOffer = (ListView)findViewById(R.id.listview_offer);

        mOfferList = new ArrayList<>();
        //Add sample data for list
        //We can get data from DB, websrevice here
        mOfferList.add(offer1);
        mOfferList.add(offer2);
        mOfferList.add(offer3);
        //Init adapter
        adapter = new OfferListAdapter(getApplicationContext(),mOfferList);
        lvOffer.setAdapter(adapter);

        lvOffer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //Do something
                double price = mOfferList.get(position).getPrice();
                int contigent = mOfferList.get(position).getContigent();
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
    }
}
