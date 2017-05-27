package com.example.david.cheapfood.Offer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.david.cheapfood.DbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 16.05.2017.
 */

public class OffersDataSource {

    private static final String LOG_TAG = OffersDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    public DbHelper dbHelper;

    private String[] columns = {
            DbHelper.COLUMN_ID,
            DbHelper.COLUMN_NAME,
            DbHelper.COLUMN_PRICE,
            DbHelper.COLUMN_DESCRIPTION,
            DbHelper.COLUMN_CONTINGENT,
            DbHelper.COLUMN_ADDRESS,
    };

    public OffersDataSource(Context context) {
        Log.d(LOG_TAG, "Create dbHelper.");
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Received database reference | Path: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
    }

    public void updateContingent(long id, long newContingent){
        Log.d(LOG_TAG, "Update contingent for offer " + id);
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_CONTINGENT, newContingent);
        database.update(DbHelper.OFFER_TABLE, values, id + "=" + DbHelper.COLUMN_ID, null);
    }

    public Offer createOffer(Offer offer) {

        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_NAME, offer.getName());
        values.put(DbHelper.COLUMN_PRICE, offer.getPrice());
        values.put(DbHelper.COLUMN_DESCRIPTION, offer.getDescription());
        values.put(DbHelper.COLUMN_ADDRESS, offer.getAddress());
        values.put(DbHelper.COLUMN_CONTINGENT, offer.getContigent());

        long insertId = database.insert(DbHelper.OFFER_TABLE, null, values);

        Cursor cursor = database.query(DbHelper.OFFER_TABLE,
                columns, DbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Offer dbOffer = cursorToOffer(cursor);
        cursor.close();

        return dbOffer;
    }

    private Offer cursorToOffer(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DbHelper.COLUMN_ID);
        int idName = cursor.getColumnIndex(DbHelper.COLUMN_NAME);
        int idPrice = cursor.getColumnIndex(DbHelper.COLUMN_PRICE);
        int idDescription = cursor.getColumnIndex(DbHelper.COLUMN_DESCRIPTION);
        int idAddress = cursor.getColumnIndex(DbHelper.COLUMN_ADDRESS);
        int idContingent = cursor.getColumnIndex(DbHelper.COLUMN_CONTINGENT);

        long id = cursor.getLong(idIndex);
        String name = cursor.getString(idName);
        double price = cursor.getDouble(idPrice);
        String description = cursor.getString(idDescription);
        long contingent = cursor.getLong(idContingent);
        String address = cursor.getString(idAddress);

        return new Offer(id, name, price, description, address, contingent);
    }

    public List<Offer> getAllOffers() {
        List<Offer> offerList = new ArrayList<>();

        Cursor cursor = database.query(DbHelper.OFFER_TABLE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Offer offer;

        while(!cursor.isAfterLast()) {
            offer = cursorToOffer(cursor);
            offerList.add(offer);
            Log.d(LOG_TAG, "Offer: " + offer.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return offerList;
    }
}