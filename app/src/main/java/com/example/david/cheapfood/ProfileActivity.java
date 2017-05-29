package com.example.david.cheapfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Julian on 29.05.2017.
 */

public class ProfileActivity extends AppCompatActivity {

    /*private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_search:
                    mTextMessage.setText(R.string.title_search);
                    startActivity(new Intent(ProfileActivity.this, SearchActivity.class));
                    return true;
                case R.id.navigation_purchaseHistory:
                    mTextMessage.setText(R.string.title_purchaseHistory);
                    startActivity(new Intent(ProfileActivity.this, PurchaseHistoryActivity.class));
                    return true;
                case R.id.navigation_favorites:
                    mTextMessage.setText(R.string.title_favorites);
                    startActivity(new Intent(ProfileActivity.this, SearchActivity.class));
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_profile);
                    return true;
            }
            return false;
        }

    };*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/

        TextView text = (TextView) findViewById(R.id.test_profile);
        text.setText("Profile Activity");

    }

}