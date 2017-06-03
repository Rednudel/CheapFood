package com.example.david.cheapfood;

import android.content.Intent;
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

import com.example.david.cheapfood.PurchaseHistory.PurchaseHistory;
import com.example.david.cheapfood.PurchaseHistory.PurchaseHistoryDataSource;
import com.example.david.cheapfood.PurchaseHistory.PurchaseListAdapter;

import java.util.List;

/**
 * Created by Julian on 29.05.2017.
 */

public class PurchaseHistoryActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ListView lvPurchase;
    private PurchaseListAdapter adapter;
    public List<PurchaseHistory> mPurchaseList;
    private PurchaseHistoryDataSource dataSource;

    private static final String LOG_TAG = PurchaseHistoryActivity.class.getSimpleName();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    startActivity(new Intent(PurchaseHistoryActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_search:
                    mTextMessage.setText(R.string.title_search);
                    startActivity(new Intent(PurchaseHistoryActivity.this, SearchActivity.class));
                    return true;
                case R.id.navigation_purchaseHistory:
                    mTextMessage.setText(R.string.title_purchaseHistory);
                    return true;
                case R.id.navigation_favorites:
                    mTextMessage.setText(R.string.title_favorites);
                    startActivity(new Intent(PurchaseHistoryActivity.this, FavoritesActivity.class));
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_profile);
                    startActivity(new Intent(PurchaseHistoryActivity.this, ProfileActivity.class));
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        dataSource = new PurchaseHistoryDataSource(this);
        lvPurchase = (ListView)findViewById(R.id.listview_purchases);

        Log.d(LOG_TAG, "open database.");
        dataSource.open();

        showAllListEntries();

        lvPurchase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //Do something
                long purchaseId = mPurchaseList.get(position).getId();
                String name = mPurchaseList.get(position).getOfferName();
                double price = mPurchaseList.get(position).getOfferPrice();
                long quantity = mPurchaseList.get(position).getQuantity();
                String orderDate = mPurchaseList.get(position).getOrderDateString();

                //Ex. display msg with product id get from view.getTag
                Toast.makeText(getApplicationContext(), "Clicked product id =" +view.getTag(), Toast.LENGTH_SHORT).show();

                //Call the next Activity
                Intent toy = new Intent(PurchaseHistoryActivity.this, PurchaseDetailsActivity.class);
                toy.putExtra("id", purchaseId);
                toy.putExtra("name", name);
                toy.putExtra("price", price);
                toy.putExtra("quantity", quantity);
                toy.putExtra("orderDate", orderDate);
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
        mPurchaseList = dataSource.getAllPurchaseHistories();

        adapter = new PurchaseListAdapter(getApplicationContext(),mPurchaseList);
        lvPurchase.setAdapter(adapter);
    }

}
