package com.example.david.cheapfood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.cheapfood.Offer.Offer;
import com.example.david.cheapfood.Offer.OfferListAdapter;
import com.example.david.cheapfood.Offer.OffersDataSource;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ListView lvOffer;
    private OfferListAdapter adapter;
    public List<Offer> mOfferList;
    private OffersDataSource dataSource;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    break;
                case R.id.navigation_search:
                    startActivity(new Intent(MainActivity.this, SearchActivity.class));
                    return true;
                case R.id.navigation_purchaseHistory:
                    startActivity(new Intent(MainActivity.this, PurchaseHistoryActivity.class));
                    return true;
                case R.id.navigation_favorites:
                    startActivity(new Intent(MainActivity.this, FavoritesActivity.class));
                    return true;
                case R.id.navigation_profile:
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        String sharedPrefs = this.getResources().getString(R.string.SHARED_PREFS);
        SharedPreferences settings = getSharedPreferences(sharedPrefs, 0);

        dataSource = new OffersDataSource(this);
        lvOffer = (ListView)findViewById(R.id.listview_offer);

        Log.d(LOG_TAG, "open database.");
        dataSource.open();

        if (settings.getBoolean("first_launch", true)) {
            Log.d("LOG_TAG", "First launch");

            Offer offer1 = dataSource.createOffer(new Offer(1,"K&U Brötchen", 0.2, "Brötchen vom Vortag","Edeka Ooser Hauptstr.6 76473 Iffezheim",10));
            Offer offer2 = dataSource.createOffer(new Offer(2,"K&U Brezen", 0.5, "Brezen vom Vortag","Edeka Ooser Hauptstr.6 76473 Iffezheim",7));
            Offer offer3 = dataSource.createOffer(new Offer(3,"K&U Tomaten Mozzarella Ciabatta", 1.5, "vor 2h belegt","Edeka Ooser Hauptstr.6 76473 Iffezheim",3));

            settings.edit().putBoolean("first_launch", false).apply();
        }

        showAllListEntries();

        lvOffer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //Do something
                long offerId = mOfferList.get(position).getId();
                double price = mOfferList.get(position).getPrice();
                long contingent = mOfferList.get(position).getContigent();
                String description = mOfferList.get(position).getDescription();
                String name = mOfferList.get(position).getName();
                String adress = mOfferList.get(position).getAddress();

                //Ex. display msg with product id get from view.getTag
                Toast.makeText(getApplicationContext(), "Clicked product id =" +view.getTag(), Toast.LENGTH_SHORT).show();

                //Call the next Activity
                Intent toy = new Intent(MainActivity.this, BuyActivity.class);
                toy.putExtra("id", offerId);
                toy.putExtra("price", price);
                toy.putExtra("contingent", contingent);
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

    @Override
    protected void onResume(){
        super.onResume();
        dataSource.open();
        showAllListEntries();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

    private void showAllListEntries () {
        mOfferList = dataSource.getAllOffers();

        adapter = new OfferListAdapter(getApplicationContext(),mOfferList);

        for (Iterator<Offer> iter = mOfferList.iterator(); iter.hasNext(); ) {
            Offer offer = iter.next();
            if(offer.getContigent() == 0){
                iter.remove();
            }
        }


        lvOffer.setAdapter(adapter);
    }

}
