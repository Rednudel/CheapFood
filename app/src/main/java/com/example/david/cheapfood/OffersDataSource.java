package com.example.david.cheapfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 16.05.2017.
 */

public class OffersDataSource {

    private static final String LOG_TAG = OffersDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private OffersDbHelper dbHelper;

    private String[] columns = {
            OffersDbHelper.COLUMN_ID,
            OffersDbHelper.COLUMN_NAME,
            OffersDbHelper.COLUMN_PRICE,
            OffersDbHelper.COLUMN_DESCRIPTION,
            OffersDbHelper.COLUMN_CONTINGENT,
            OffersDbHelper.COLUMN_ADDRESS,
    };

    public OffersDataSource(Context context) {
        Log.d(LOG_TAG, "Create dbHelper.");
        dbHelper = new OffersDbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Get database. Path: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "database closed.");
    }

    public Offer createOffer(Offer offer) {

        ContentValues values = new ContentValues();
        values.put(OffersDbHelper.COLUMN_NAME, offer.getName());
        values.put(OffersDbHelper.COLUMN_PRICE, offer.getPrice());
        values.put(OffersDbHelper.COLUMN_DESCRIPTION, offer.getDescription());
        values.put(OffersDbHelper.COLUMN_ADDRESS, offer.getAddress());
        values.put(OffersDbHelper.COLUMN_CONTINGENT, offer.getContigent());

        long insertId = database.insert(OffersDbHelper.TABLE_OFFER_LIST, null, values);

        Cursor cursor = database.query(OffersDbHelper.TABLE_OFFER_LIST,
                columns, OffersDbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Offer dbOffer = cursorToOffer(cursor);
        cursor.close();

        return dbOffer;
    }

    private Offer cursorToOffer(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(OffersDbHelper.COLUMN_ID);
        int idName = cursor.getColumnIndex(OffersDbHelper.COLUMN_NAME);
        int idPrice = cursor.getColumnIndex(OffersDbHelper.COLUMN_PRICE);
        int idDescription = cursor.getColumnIndex(OffersDbHelper.COLUMN_DESCRIPTION);
        int idAddress = cursor.getColumnIndex(OffersDbHelper.COLUMN_ADDRESS);
        int idContingent = cursor.getColumnIndex(OffersDbHelper.COLUMN_CONTINGENT);

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

        Cursor cursor = database.query(OffersDbHelper.TABLE_OFFER_LIST,
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